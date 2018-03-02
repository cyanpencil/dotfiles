## Installation

Install `atom` using the instructions for you platform, then open atom and click
on the `antenna icon` on the bottom right of the editor to configure `teletype`.

### MacOS

```bash
# Uncomment if you don't have Homebrew or Homebrew Cask installed on your machine.
# /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
# brew install caskroom/cask/brew-cask

brew cask install atom

apm install teletype
apm install open-terminal-here highlight-selected sublime-style-column-selection
apm install auto-detect-indentation editorconfig

atom
```

### Windows

```bash
# Uncomment if you don't have Chocolatey installed on your machine.
# @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"

cinst Atom

apm install teletype
apm install open-terminal-here highlight-selected sublime-style-column-selection
apm install auto-detect-indentation editorconfig

atom
```

### Arch

```bash
sudo packman -S atom

apm install teletype
apm install open-terminal-here highlight-selected sublime-style-column-selection
apm install auto-detect-indentation editorconfig

atom
```

### Ubuntu

```bash
sudo add-apt-repository ppa:webupd8team/atom
sudo apt update
sudo apt install atom

apm install teletype
apm install open-terminal-here highlight-selected sublime-style-column-selection
apm install auto-detect-indentation editorconfig

atom
```
