echo "\nThis script will install qiv (image viewer) and Wireshark. Please select <Yes> during Wireshark installation.\n"
# install qiv
sudo apt-get install qiv

# install wireshark

sudo add-apt-repository ppa:wireshark-dev/stable
sudo apt-get update
sudo apt-get install wireshark
sudo usermod -a -G wireshark $USER
echo "\nYou need to log out and log back in for Wireshark to capture packets."