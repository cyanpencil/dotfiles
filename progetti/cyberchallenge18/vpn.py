#!/usr/bin/env python

#############################################
# Hacking-Lab Python commandline VPN Client #
# https://github.com/ragerin/hl-pyvpn       #
#                                           #
# Authors:                                  #
#                                           #
# Ragerin - https://github.com/ragerin      #
#                                           #
# Original code                             #
# Zy0d0x - zy0d0x@nullsecurity.net          #
#############################################


banner = """
Hacking-Lab Python commandline VPN Client
"""

hostname = '212.254.246.102'
port = '443'
certificate = 'hlca.crt'

log_openvpn = False

log_dir = '/var/log/hl_pyvpn/'
default_logfile = 'log_hl_pyvpn.log'
openvpn_logfile = 'log_hl_openvpn.log'
exception_logfile = 'log_hl_exception.log'


try:
    import sys, os, time
    import getpass
    import pexpect
    import shutil
    import argparse
except ImportError as e:
    msg = '[-] An ImportError has occurred. Please review the \'' + exception_logfile + '\' for details.'
    print(msg)
    try:
        if not os.path.exists(log_dir):
            os.makedirs(log_dir)
    except OSError as e:
        if e.errno != errno.EEXIST:
            raise
        sys.exit(1)
    with open(os.path.join(log_dir, default_logfile), 'a') as logfile:
        logfile.write(msg + '\n')
    with open(os.path.join(log_dir, exception_logfile), 'a') as elogfile:
        elogfile.write(str(e.args[0]))
    sys.exit(1)



def log(msg, file_path=default_logfile):
    if not os.path.exists(log_dir):
        try:
            os.makedirs(log_dir)
        except OSError as e:
            if e.errno != errno.EEXIST:
                raise
            sys.exit(1)
    with open(os.path.join(log_dir, file_path), 'a') as logfile:
        logfile.write(time.strftime('%Y-%m-%d, %H:%M:%S') + ' | ' + msg + '\n')



argparser = argparse.ArgumentParser(description='Establishes a VPN connection to Hacking-Lab.com, for use with their hacking challenges.', formatter_class=argparse.ArgumentDefaultsHelpFormatter)
argparser.add_argument('--hostname', type=str, default=hostname, help='sets the host address')
argparser.add_argument('--port', type=str, default=port, help='sets the port of the host')
argparser.add_argument('--certificate', type=str, default=certificate, help='path to the certificate file')
argparser.add_argument('--logdir', type=str, default=log_dir, help='dir to place log files')
argparser.add_argument('--logvpn', action='store_true', help='if specified, all openvpn output is logged in ' + openvpn_logfile)

args = argparser.parse_args()


if args.hostname:
    hostname = args.hostname
if args.port:
    port = args.port
if args.certificate:
    certificate = args.certificate
if args.logdir:
    log_dir = args.logdir
if args.logvpn:
    log_openvpn = True




def backup_file(file_path):
    if os.path.isfile(file_path):
        try:
            shutil.copy2(file_path, file_path + '.original')
            log('Backup of ' + file_path + ' successful')
        except:
            log('Error taking backup of file ' + file_path)
            raise
    else:
        log('File ' + file_path + ' not found, therefore no backup was created')


def restore_file(file_path):
    if os.path.isfile(file_path + '.original'):
        try:
            shutil.copy2(file_path + '.original', file_path)
            log('Backup of ' + file_path + ' restored successfully')
            os.remove(file_path + '.original')
            log('Backup of ' + file_path + ' was removed')
        except:
            log('Error restoring original file from backup')
            raise
    else:
        log('No backup of file ' + file_path + ' was found')
 

def write_config(file_path):
    print('\n[+] Writing Configuration File')
    with open(file_path, 'w') as file:
        file.write('client \n')
        file.write('dev tun \n')
        file.write('proto tcp \n')
        file.write('remote ' + hostname + ' ' + port + '\n')
        file.write('ns-cert-type server \n')
        file.write('resolv-retry infinite \n')
        file.write('nobind \n')
        file.write('persist-key \n')
        file.write('persist-tun \n')
        file.write('ca ' + certificate + '\n')
        file.write('auth-user-pass \n')
        file.write('auth-nocache \n')
        file.write('verb 1')
    print('\t - Done')

def write_resolvconf(file_path):
    print('\n[+] Writing Resolv.conf')
    with open(file_path, 'w') as conf:
        conf.write('domain hacking-lab.com \n')
        conf.write('search hacking-lab.com \n')
        conf.write('nameserver 192.168.200.193')
    print('\t - Done')



try:
   if os.geteuid() != 0:
      print('This script must be run with root privileges.\n')
      sys.exit(1)
   else:
        print(banner)
        username = "lucadb96@gmail.com"
        if username == '':
           print('\n\n[-] Missing username')
        else:
             password=getpass.getpass('Password: ') 
             if password == '':
                print('\n\n[-] Missing password')
             else:
                  log('**** Script started *****')
                  backup_file('/tmp/config.ovpn')
                  write_config('/tmp/config.ovpn')
                  
                  backup_file('/etc/resolv.conf')
                  write_resolvconf('/etc/resolv.conf')

                  print('\n[+] Connecting to HL VPN...')

                  execute = pexpect.spawn('openvpn /tmp/config.ovpn', echo=False, logfile=open(os.path.join(log_dir, openvpn_logfile), 'ab') if log_openvpn else None)
                  execute.expect('Enter Auth Username:')
                  execute.sendline(username)
                  execute.expect('Enter Auth Password:')
                  execute.sendline(password)
                  execute.expect('Initialization Sequence Completed')

                  print('\t - Done')


                  print('\n[+] Connected - press Ctrl-C to exit client.')
                  execute.interact(output_filter=lambda _: '')

                  raise KeyboardInterrupt



except KeyboardInterrupt:
    print('\n[-] Exiting Hacking-Lab VPN script')
    log('**** Quit by keyboard interrupt ****')

except pexpect.EOF as e:
    msg = '[-] An EOF exception occurred while spawning the OpenVPN instance.\nUsually this occurs due to a wrong or missing path to the certificate file.\nThe full error was logged in \'' + exception_logfile + '\' for further inspection.'
    print('\n' + msg)
    log(msg)
    log(e.args[0], exception_logfile)

except pexpect.TIMEOUT as e:
    msg = '[-] A TIMEOUT exception occurred. Read time exceeded its limit.i\nFull error was logged in \'' + exception_logfile + '\' for further inspection.'
    print('\n' + msg)
    log(msg)
    log(e.args[0], exception_logfile)

except:
    msg = '[-] An unhandled exception occurred, and can be found in \'' + exception_logfile + '\''
    print('\n' + msg)
    log(msg)
    log(str(sys.exc_info()), exception_logfile)

finally:
    restore_file('/tmp/config.ovpn')
    restore_file('/etc/resolv.conf')
    log('**** Script gracefully ended ****')
