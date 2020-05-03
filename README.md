# GitHub-Automation

<h4 align="center"> The GitHub Automater speeds up the process of starting new coding projects by creating a new repository 
and linking it to a folder on your desktop. It even opens Visual Studio Code when it's finished!<br />This applications <em>DOES NOT</em> create a new GitHub Account, only a repository for an established account.</h4>


# Demo
👉 Watch it <a 
href="">here</a>.

# Prerequisites
### Windows
- GitHub Account
- Git Commands for Command-Line usage
- Java 7
- Windows OS
- Google Chrome
- Visual Studio Code

### Linix or Mac
I apologize. I only have access to the Windows platform. Coming soon...

# For Developers
Clone the source locally:

```sh
$ git clone https://github.com/ComputerScience2FinalProject/GitHub-Automation/
$ cd GitHub-Automation
```

## Features
- [x] One time login (GitHub likes device authentication 😜)
- [x] Opens Project in VSCode to speed up your development needs
- [ ] Cross Platform

## Usage


Update UserCredentials.txt with your GitHub account Username and Password
<p>
<em>Keep this file safe! It will contain private information.</em>
</p>

Ex:
>fakeUsername <br />
fakePassword
---

This program runs from the command line.

```sh
$ java Create
```
---
**Two-Factor Authentication must be disabled.** 

This program will work unless the GitHub account used is recognized by the device and the user is logged in to the GitHub account in the Command-Line. When the user is logged on in both of those places, GitHub-Automation will work.

<p align="center">
    <img src="images\Command_Line_login.png" width="350px" alt="Command Line Login"/>
    <br />
    <img src="images\gitHubDeviceVerificaton.jpg" width="350px" alt="Device"/>
</p>


## Built With
- [Selenium](https://selenium.dev)

# Authors
<a href=https://github.com/aking618>Ayren King </a>
