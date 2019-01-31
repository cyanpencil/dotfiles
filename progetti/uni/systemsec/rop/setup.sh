#!/bin/sh

echo "Creating the rop executable"
make clean
make

if [ -e "somefile.sh" ]; then
echo "Deleting old file..."
rm -f somefile.sh
fi

echo "Creating somefile.sh..."

echo "#!/bin/sh" > somefile.sh
echo "echo \"Super secret password is syssec\" " >> somefile.sh
sudo chown root somefile.sh
sudo chmod 600 somefile.sh

echo "Done."
