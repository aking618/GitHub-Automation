# GitHub-Automation

<h4 align="center">
    The GitHub Automater speeds up the process of starting new coding projects by creating a new repository 
and linking it to a folder on your desktop or deleting a current repository. It even opens Visual Studio Code when it's finished creating!<br />This applications <em>DOES NOT</em> create a new GitHub Account, only a repository for an established account.</h4>
<h5 align="center">
    I plan on further improving this program with support for other web browsers as well as add encryption to UserCredentials to offer greater security.
</h5>


# Prerequisites
### Windows
- GitHub Account
- Git
- Java 11 or higher
- Windows OS
- Google Chrome 
- Visual Studio Code (Not required for the creation of the repository. This is my preferred text editor.)

### Linix or Mac
I apologize. I only have access to the Windows platform. Coming soon...

## Features
- [x] One time login (GitHub likes device authentication 😜)
- [x] Opens Project in VSCode to speed up your development needs
- [ ] Multi-Web Browser Support (Coming Soon)

# For Developers
## Usage

**Two-Factor Authentication must be disabled on the GitHub account used with this Program.** 

This program will not work unless the GitHub account used is recognized by the device and the user is logged in to the GitHub account in the Command-Line. When the device is recognized by  the GitHub account, the program will work as intended. Command-Line may prompt the user to log in (This is a one-time login). 

<p align="center">
    <img src="images\gitHubDeviceVerificaton.jpg" width="350px" alt="Device"/>
    <br />Device Verification Popup<br />
    <img src="images\Command_Line_login.png" width="350px" alt="Command Line Login"/>
    <br />Command-Line Login Popup<br />
</p>

Clone the source locally:

```sh
$ git clone https://github.com/Aking618/GitHub-Automation/
$ cd GitHub-Automation
```

Update UserCredentials.txt with your GitHub account Username and Password. You can use the following command to edit the file in notepad or edit the text in your preferred text editor.

```sh
$ notepad UserCredentials.txt
```
<p>
<em>Keep this file safe! It will contain private information.</em>
</p>

Ex:
>fakeUsername <br />
fakePassword





___

This program runs from the command line. Choose either "create" or "delete". Make sure you are in the root directory "GitHub-Automation"

```sh
$ java -jar target/github-automation-1.0.0.jar [create or delete] [fileName]
```
---

####Create

<p>
Google Chrome will be opened, maximized, and navigated to the GitHub login page. The User info provided in UserCredentials.txt will be used to login. Next, a new, private repository will be created with desired name and with a README.md file included.
</p>
<p>
This message will appear in the terminal. It does not impact the code or cause any error. "SEVERE" replaced the older version of Selenium's "WARNING". This message appears because there was more than a tenth of a second between actions.
</p>

```sh
[1588654880.307][SEVERE]: Timed out receiving message from renderer: 0.100
```

<p>
Then, Google Chrome is closed and the Command-Line is opened.  A batch file is created with all of the necessary commands to create and navigate into the desired directory and it also runs through the Git commands to link the repository with the local folder.
</p>

<p>
Finally, Visual Studio Code is opened in the local directory to begin the project.
</p>


---

####Delete

<p>
Google Chrome will be opened, maximized, and navigated to the GitHub login page. The User info provided in UserCredentials.txt will be used to login. Next, the page is navigated to the desired repository's settings page where it will be deleted.
</p>
<p>
This message will appear in the terminal. It does not impact the code or cause any error. "SEVERE" replaced the older version of Selenium's "WARNING". This message appears because there was more than a tenth of a second between actions.
</p>

```sh
[1588654880.307][SEVERE]: Timed out receiving message from renderer: 0.100
```

<p>
Then, Google Chrome is closed. The local folder will NOT be removed.
</p>

---



## Built With
- [Apache Maven](http://maven.apache.org/)
- [Selenium](https://selenium.dev)

# Authors
<a href=https://github.com/aking618>Ayren King </a>
