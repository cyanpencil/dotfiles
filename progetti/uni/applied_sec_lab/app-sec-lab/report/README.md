
## Employee
- from the client, open the browser and visit https://ca.imovies.com
- to import a client certificate: Preferences > Advanced > Certificates > View Certificates > Your Certificates > Import (empty password)
- once a certificate revoked, wait a minute to see the effect

## CA admin
- import certificate `/home/vagrant/admin.pfx`, password "aaaa"

## Sysadmin
- `ssh ca.imovies.com` from terminal in client
- passphrase for all keys is `hunter2` for testing purposes
- `ssh -lroot <host>` for `webserver`, `firewall`, `core`, `database` or `backup`
- encrypted database backup and logs are in `/var/backup/` on the `backup` host
- can be decrypted using the key in this folder
