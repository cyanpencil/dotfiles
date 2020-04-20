### Use cases / tests

- user/employee
  - log in with password, see info in database and download certificate
  - generate new certificate
  - log in with certificate, edit name, password and email
  - log in with password or certificate, revoke any certificate, log out and cannot log in with revoked certificate anymore
- CA admin
  - log in on webserver with certificate, see stats (serial number??)
- system admin
  - connect on any machine via ssh with certificate and restart the services
  - download backup archive from backup machine, upload on database and restore
