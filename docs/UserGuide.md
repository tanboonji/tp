---
layout: page
title: A-Bash Book User Guide
navigation_title: User Guide
---

# Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# What is A-Bash Book?

A-Bash Book (ABB) is a Command Line Interface (CLI) based Employee and Business Contact Management System.
CLI is a text-based interface where you can enter commands to perform various functions such as adding contact details into ABB.

ABB is built to address the growing demands of businesses especially in a climate where large amounts of business information are being stored in various places. As the need to centralise data and optimise workflow increases, businesses are challenging current standards of retrieving operational data to achieve minimum lead times.

ABB acts as a centralised platform where you can store the contact details of people you work with, such as your colleagues and business partners. For starters, in ABB you can add, edit, and delete contacts. As you continue reading this guide, you will find that ABB offers features such as auto-complete to ease your contact management process.

Fun fact: Bash is a highly popular CLI-based program. ABB utilises command patterns similar to Bash, hence the name, A-Bash Book (ABB).
With similarities to Bash, we hope to minimise the learning curve for people with existing Bash experience.

--------------------------------------------------------------------------------------------------------------------

# How to Use the User Guide

You can use the [Table of Contents](#table-of-contents) to quickly navigate around the User Guide.

Before you continue reading the rest of the User Guide, take note of the following text formats and conventions which are used to place emphasis on certain texts:

| Text Format                                                              | Meaning                         |
| ------------------------------------------------------------------------ | ------------------------------- |
| `Code`                                                                   | Command text                    |
| <kbd>Keyboard Input</kbd>                                                | Keyboard actions                |
| **Bolded Text**                                                          | Important words to take note of |
| <div markdown="span" class="alert alert-info">:information_source:</div> | Tips and useful information     |
| <div markdown="span" class="alert alert-danger">:exclamation:</div>      | Warning message                 |

--------------------------------------------------------------------------------------------------------------------

# Quickstart Guide

1. Ensure that **Java 11** or above is installed in your computer.

1. Download the latest **abashbook.jar** from [here](https://github.com/AY2021S2-CS2103T-T12-3/tp/releases).

1. Copy the **abashbook.jar** to your preferred target folder to use as the _home folder_ to contain the A-Bash Book data.
   See [FAQ: What is the Home Folder?](#what-is-the-home-folder) to understand more.

1. Double-click the **abashbook.jar** to start the app. The program window should appear in a few seconds.
   Note that the app contains some sample data, as seen in the picture below. <br>
   ![Ui](images/UG_UI%20Guide.png)
   ![Person List Reference ><](images/UG_UI%20Reference.png)

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.<br>
   Some example commands to try:

    * **`help`** : Opens the [Command Summary](#command-summary) in the app.

    * **`list`** : Lists all contacts.

    * **`add`**: `-n John Doe -p 98765432 -e johnd@example.com -c Google -j HR Manager -a John street` : Adds a contact named `John Doe` to the address book.

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# CLI Syntax

<div markdown="block" class="alert alert-info">
**:information_source: Note for seasoned CLI users:**<br>

This section is intended for non-technical users to understand the CLI syntax. While A-Bash Book
adopts Bash syntax, the implementation is not in parity with Bash's syntax. Feel free to skip
to [Features](#features).

</div>

Command Line Interface (CLI) is a text-based interface in which commands are entered. A-Bash Book is
CLI-based and will only interpret commands that are structured properly. This is called the
**syntax** where a specific arrangement of words and arguments form a command that A-Bash Book can
understand.

## Examples of Command

Here are some examples of how a command is documented in A-Bash Book:

- `select { clear | shown | show | INDEX... }`
- `edit { shown | selected | INDEX... } [-n NAME] [-r REMARK] [-t TAG]...`
- `tag delete { shown | selected | INDEX... } -t TAG [-t TAG]...`

## Command Flags

Command flags are specific options that can be specified to add more information in a command.

E.g `add -n John Doe -p 94326543 -e john@doe.com -a 45 Address #06-90`

Command: `add`

Command flags: `-n` , `-p`, `-e`, `-a`.


## Uppercase Words

Words in `UPPER_CASE` are the parameters to be supplied by the user.

e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

## Optional Parameters

Square brackets `[ ]` are used around an optional parameter.

If there are multiple optional parameter, each argument is enclosed in its own set of square
brackets.

e.g `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe` but
not `-t friend`.

## Parameters That Can Repeat

Ellipsis (`...`) indicates the option to use a parameter multiple times, including zero times.

e.g. `[-t TAG]...` can be used as `""` (i.e. 0 times), `-t friend` (i.e. 1 time), `-t friend -t family` (i.e. 2 times) etc.

e.g. `-t TAG [-t TAG]...` specifies that there must be **at least one** tag and can be used
as `-t family` and `-t family -t cousin -t child`.

## Mutually Exclusive Parameters

Braces (`{ }`) are used around arguments where the user must choose **only one** of the items inside the braces.

Vertical bars (`|`) are used to separate the items. There can be more than two
mutually exclusive parameters.

e.g. `select { clear | shown | show | INDEX...}` can be used as:

- `select clear`
- `select shown`
- `select show`
- `select 1`
- `select 1 2 3`

Invalid examples are:

- `select clear show`
- `select clear 1 2 3`

## Miscellaneous Information

- If a parameter is expected only once in the command, but is specified multiple times, only the
  last occurrence of the parameter will be taken.<br>
  e.g. `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`
  and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- `shown` is a special index that refers to all persons that are visible in the filtered person
  list. e.g. `find surname` shows 10 persons. Executing `select shown` will select all 10 of the
  persons.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

For non-technical users, please refer to the [CLI Syntax](#cli-syntax) before proceeding.

</div>

## Viewing help : `help`

Displays the entire User Guide for ease of reference.

![Help Message UI](images/helpMessage.png)

**Format**: `help`


## Adding a person: `add`

Adds a person to the address book.

**Format**: `add -n NAME -p PHONE_NUMBER -e EMAIL -c COMPANY -j JOB_TITLE -a ADDRESS [-r REMARK] [-t TAG]...`

<div markdown="block" class="alert alert-info">

:information_source: **Notes on `add` command:**
* A person can have no remark.
* A person can have any number of tags (including 0).

</div>

![Add Command UI](images/UG_Add%20Command.png)

**Examples**:

| Example                                                                                                                                | Description                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `add -n John Doe -p 98765432 -e johnd@example.com -c Google -j HR Manager -a John street`                                              | Adds a person named `John Doe`, with phone number `91234567`, email address `johndoe@example.com`, company `Google`, job title `HR Manager`, and address `John street`.                                                                                                       |
| `add -n Betsy Crowe -p 1234567 -e betsycrowe@example.com -c Amazon -j Manager -a Betsy Avenue -t Recruiter -t Manager`                 | Adds a person named `Betsy Crowe`, with phone number `1234567`, email address `betsycrowe@example.com`, company `Amazon`, job title `Manager`, and address `Betsy Avenue`. This person is also tagged with the following tags: `Recruiter` and `Manager`.                     |
| `add -n Charlie -p 7654321 -e charlie@example.com -c Facebook -j Software Engineer -a Charlie Road -t IT -r Emergency contact`         | Adds a person named `Charlie`, with phone number `7654321`, email address `charlie@example.com`, company `Facebook`, job title `Software Engineer`, and address `Charlie Road`. This person is also tagged with the tag `IT`, and has the remark `Emergency contact`.         |

## Listing all persons : `list`

Lists all persons in the address book.

**Format**: `list`

## Editing a person : `edit`

Edits an existing person in the address book.

**Format**: `edit { shown | selected | INDEX... } [-n NAME] [-p PHONE] [-c COMPANY] [-j JOB_TITLE] [-e EMAIL] [-a ADDRESS] [-r REMARK] [-t TAG]...`

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `edit` command:**<br>

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer**, i.e 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing remark, the existing remark of the person will be removed, i.e adding of remark is not cumulative.
* When editing tags, the existing tags of the person will be removed, i.e adding of tags is not cumulative.
* To remove the person’s remark, type `-r ` without specifying any remark after it.
* To remove all the person’s tags, type `-t ` without specifying any tags after it.
* To edit all the shown person, type `edit shown`
* To edit all the selected person, type `edit selected` followed by the arguments

</div>

![Edit Command UI](images/UG_Edit%20Command.png)

**Examples**:

| Example                                     | Description                                                                                                         |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| `edit 1 -p 91234567 -e johndoe@example.com` | Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively. |
| `edit 2 -n Betsy Crower -t `                | Edits the name of the 2nd person to be `Betsy Crower` and clears all of her existing tags.                          |
| `edit 3 -r `                                | Clears any existing remark of the 3rd person.                                                                       |
| `edit shown -r `                            | Clears any existing remark of all the displayed persons in person list.                                             |
| `edit selected -r `                         | Clears any existing remark of all the selected persons.                                                             |

<div markdown="block" class="alert alert-info">

**:bulb: Bulk Edit**

To bulk edit, either do:
* `edit 1 2 3` to edit persons at indexes 1, 2 and 3 or,
* `edit shown` to edit all the shown persons or,
* `edit selected` to edit all the selected persons

</div>

## Locating persons: `find`

Finds persons whose field(s) contain any of the given keywords.
<div markdown="block" class="alert alert-info">

**:information_source: Currently Searchable fields:** Name, Email, Tag, Remark<br>

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `find` command:**<br>

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Words are partially matched. e.g `sam` will match `Samantha`
* Similar words are matched. e.g `Shawn` with match `Shaun`
* Partially similar words will also be matched as a result of the above. e.g `Ben` will match `Elizabeth`
    * `bet` in `Elizabeth` is 1 character away from `Ben`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

</div>

![Find Command UI](images/UG_Find%20Command.png)

### Searching all searchable fields

**Format**: `find KEYWORD [MORE_KEYWORDS]`

**Examples**:

| Example           | Description                                                                                                                                       |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| `find Jon`        | Returns any person that matches `jon` partially in any of the searchable fields<br> e.g. a person tagged as `Janitor` (`Jon` is similar to `Jan`) |
| `find alex david` | Returns any person that matches `alex` or`david` partially in any of the searchable fields<br> e.g. people named `Alex Yeoh`, `David Li`          |

### Searching by specific fields

**Format**: `find FIELD_PREFIX KEYWORD [MORE_KEYWORDS]`

<div markdown="block" class="alert alert-info">

:information_source: Refer to [Field Summary](#field-summary) for a full list prefixes.<br>
**Currently Searchable fields:** Name, Email, Tag, Remark

</div>

<div markdown="block" class="alert alert-info">

:information_source: You can only search 1 field at a time.<br>
`find -n Alice -t HR` is an invalid command

</div>

**Examples**:

| Example             | Description                                                                                               |
| ------------------- | --------------------------------------------------------------------------------------------------------- |
| `find -n Alice Ben` | Returns people named `Alicia Yen` (Similar) and `Benjamin Koh` (Partial)                                  |
| `find -t Market`    | Returns people tagged with `Marketing` (Partial)                                                          |
| `find -r Manager`   | Returns people with `Management Intern` (Similar) and `Human Resource Manager` (Partial) in their remarks |


## Deleting a person : `delete`

Deletes the specified person(s) from the address book.

**Format**: `delete { shown | selected | INDEX... }`

<div markdown="block" class="alert alert-info">

**:information_source: Notes on `delete` command:**<br>

* Deletes the person at the specified `INDEX`/`INDEX...` or shown person list or selected list.
* The command **operates on the shown list** that may be modified by an earlier command.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer**, i.e 1, 2, 3, ...
* To delete all the shown person, type `delete shown`
* To delete all the selected person, type `delete selected`

</div>

![Delete Command UI](images/UG_Delete%20Command.png)

**Examples**:

| Example                                      | Description                                                                                                           |
| -------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| `list`<br>`delete 2`                         | `list` displays all entries.<br>`delete 2` deletes the second entry in the list shown.                                |
| `find Betsy`<br>`delete 1`                   | `find Betsy` filters entries to the find result.<br> `delete 1` deletes the first entry in the filtered results list. |
| `select 1 2 3`   <br>      `delete selected` | Deletes selected entries 1, 2 and 3                                                                                   |
| `delete shown`                               | Deletes all the displayed persons in the person list                                                                  |

<div markdown="block" class="alert alert-info">

**:bulb: Bulk Delete**

To bulk delete, either do `delete 1 2 3` to delete indexes 1, 2 and 3
or `delete shown` to delete all the shown persons.

</div>

## Selecting Persons : `select`

Enables user to incrementally select multiple person objects to apply actions on.

**Format**: `select { clear | shown | show | INDEX... }`

Sub Command Format:
* `select show`
* `select clear`
* `select shown`
* `select INDEX...`

The selected person(s) will have a highlighted index number to indicate selection status.

![Select UI Example](images/UG_Select%20Indicator.png)
![select demo](images/ug_select%20command.png)

Examples:

| Example        | Description                                                                                                                        |
| -------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `select show`  | Shows the selected person(s)                                                                                                       |
| `select shown` | Selects all the person in the visible person list (visible person list refers to the list of persons that are currently displayed) |
| `select clear` | Clears the current selection                                                                                                       |
| `select 1`     | Select the person with index 1 (incremental selection)                                                                             |
| `select 1 2`   | Select persons with index 1 and 2 (incremental selection)                                                                          |

<div markdown="block" class="alert alert-info">

**:information_source: Incremental Selection and `shown`:**

- Incremental selection means that selected items are "stacked" together. e.g. Executing `select 1`
  followed by `select 2` will result in person with index number 1 and 2 being selected.

- `shown` refers to all the person objects in the current visible person list (visible person list
  refers to the list of persons that are currently displayed).

</div>

<div markdown="block" class="alert alert-info">

**:bulb: Tip:**

`select` command is best used with the following commands:

- [`edit` command](#editing-a-person--edit)
- [`delete` command](#deleting-a-person--delete)
- [`tag` command](#tag--tag)
- [`email` command](#email--email)

</div>

## Email Persons: `email`

Email command allows user to email selected persons using the operating system's default email
client. Email client must be configured to allow command separated email values.

<div markdown="block" class="alert alert-info">

:information_source: It is a known issue that Outlook for Windows do not have "Commas to Separate
Multiple Email Recipients" feature turned on. Follow the guide here to enable
it: <https://www.lifewire.com/commas-to-separate-email-recipients-1173680>

information_source: It is not possible for A-Bash Book to detect if the email client is opened. If
executing `email` command does not trigger the email client to appear, please check that an email
client is installed and configured properly.

</div>

**Format**: `email { shown | selected | INDEX... }`

![Email example](images/UG_Email%20Command.png)

**Examples**:

| Example          | Description                               |
| ---------------- | ----------------------------------------- |
| `email shown`    | Email all persons show in the person list |
| `email selected` | Email selected persons                    |
| `email 1`        | Email person at index 1                   |
| `email 1 2`      | Email person at index 1 and 2             |


## Clearing all entries : `clear`

Clears all entries from the address book.

<div markdown="span" class="alert alert-danger">

:exclamation: **This action is irreversible.** Do not run this command with actual data unless you want to delete all entries.

</div>

**Format**: `clear`

## Exiting the program : `exit`

Exits the program.

**Format**: `exit`

## Autocomplete

### Commands

![Command Autocomplete](images/UG_Autocomplete.png)

Commands in the command box can be autocompleted by pressing the <kbd>Tab</kbd> key.

**Examples**:

To execute the command `delete`,

Typing `del` followed by <kbd>Tab</kbd> will auto complete `del` to `delete`.

| Current text in command box | Key Press | Result |
| ------------------------------ | -------------| ----|
| `ad` | <kbd>TAB</kbd> | `add` |
| `edi` | <kbd>TAB</kbd> | `edit` |
| `ex` | <kbd>TAB</kbd> | `exit` |
| `he` | <kbd>TAB</kbd> | `help` |
| `li` | <kbd>TAB</kbd> | `list` |
| `s` | <kbd>TAB</kbd> | `select` |


<div markdown="block" class="alert alert-info">

:information_source: In the command box, it is also possible to cycle through **Existing** commands with <kbd>Tab</kbd>.

</div>

### Flags

Command flags can be autocompleted at the end of your command text by pressing the <kbd>Tab</kbd> key.

<div markdown="block" class="alert alert-info">

:information_source: **Note on flags:**

- If your flag has no content, the next available flag will be replaced and cycled.
- If you flag has content, the next available flag will be appended to your command text.

</div>

| Supported Commands                        | Available command flags|
| ------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `add`                       | `-n` , `-p`, `-e`, `-a`, `-r`, `-t` |
| `edit`                       | `-n` , `-p`, `-e`, `-a`, `-r`, `-t` |

<div markdown="block" class="alert alert-info">

:information_source: **Note on `add` command:** 

- a space character should follow the add command for flags to begin 
autocompletion.

</div>

| Current text in command box | Key Press | Result |
| ------------------------------ | -------------| ----|
| `add␣` | <kbd>TAB</kbd> | `add -n` |
| `add -n` | <kbd>TAB</kbd> | `add -p` |
| `add -n John Doe` | <kbd>TAB</kbd> | `add -n John Doe -p` |
| `add -n John Doe -p 94326543 -e john@doe.com` | <kbd>TAB</kbd> | `add -n John Doe -p 94326543 -e john@doe.com -a ` |
| `edit 1` | <kbd>TAB</kbd> | `edit 1 -n` |
| `edit 1 -n` | <kbd>TAB</kbd> | `edit 1 -p` |
| `edit 1 -n John Dow` | <kbd>TAB</kbd> | `edit 1 -n John Dow -p` |

### Index

<div markdown="block" class="alert alert-info">

:information_source: Autocomplete index only works for the `EDIT` and `DELETE` commands.

</div>

Index can be autocompleted by pressing the <kbd>UP/DOWN</kbd> keys when the supported commands are fully typed out.

**Examples**

| Current text in command box | Key Press | Result |
| ------------------------------ | -------------| ----|
| `edit` | <kbd>UP/DOWN</kbd> | `edit 1` |
| `edit 1` | <kbd>DOWN</kbd> | `edit 2` |
| `edit 2` | <kbd>UP</kbd> | `edit 1` |
| `add` | <kbd>UP/DOWN</kbd> | `add 1` |
| `add 1` | <kbd>DOWN</kbd> | `add 2` |
| `add 2` | <kbd>UP</kbd> | `add 1` |

## Aliasing commands : `alias`

The alias command allows you to create shortcut command (also known as command alias) to the actual command.

### Add an alias: `alias add`

Adds an alias to address book.

**Format**: `alias add ALIAS COMMAND`

![Alias Add](images/UG_Alias%20Add%20Command.png)

**Examples**:

| Example                             | Description                                                                                                                                                                                |
| ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `alias add ls list`                 | Associates a new `ls` command to list, such that the `ls` command will behave identically to the list command (i.e ls will now generate the list of all contacts).                           |
| `alias add ls list -n  -p  -e  -t` | Associates a new `ls` command to list, such that the `ls` command will behave identically to the list command with the options (i.e `list -n  -p  -e  -t`).                                |
| `alias add f find`                  | Associates a new `f` command to `find`, such that the `f` command will behave identically to the `find` command (i.e f Alex Yeoh will now return contacts equals or similar to Alex Yeoh). |

### Delete an alias: `alias delete`

Deletes an existing alias from address book.

**Format**: `alias delete ALIAS`

![Alias Delete](images/UG_Alias%20Delete%20Command.png)

**Examples**:

| Example           | Description            |
| ----------------- | ---------------------- |
| `alias delete ls` | Removes the alias `ls` |
| `alias delete f`  | Removes the alias `f`  |

### List all aliases: `alias list`

Lists all alias(es) in the address book.

**Format**: `alias list`

:information_source: The Command List Panel will show a list of your aliases when you execute the `alias list`. On your next
keyboard action, the list of your aliases will disappear and show the list of existing commands again.

![Alias List](images/UG_Alias%20List%20Command.png)

## Filter Field Visibility: `filter`

Filter command toggles visibility of fields based on user input options.

**Format**: `filter [-OPTION]...`

![Filter Command UI](images/UG_Filter%20Command.png)

Each option should start with a hyphen `-` e.g. `-OPTION` and be separated by a white-space. Options
which are excluded will be hidden. Refer to [Field Summary](#field-summary) for all the available
options.

<div markdown="span" class="alert alert-info">

:information_source: Refer to [Field Summary](#field-summary) for the available options

</div>

**Examples**:

| example        | description                                              |
| -------------- | -------------------------------------------------------- |
| `filter`       | Shows all fields.                                         |
| `filter -a`    | shows the contact's name and address only.               |
| `filter -a -p` | shows the contact's name, address and phone number only. |

## Tagging persons: `tag`

The tag command allows you to add and delete specific tags of persons.

* Tags are [case-insensitive](#glossary). e.g. `Photoshop` tag and `photoshop` tag are treated as the same tag.
* Same tags cannot be added to a person. e.g. A person cannot have both `Photoshop` and `photoshop` tags.

### Add tags to persons: `tag add`

Add tags to persons in address book.

* A `tag add` command adding the same `photoshop` tag to a person with `Photoshop` tag will be executed successfully.
* When adding a same tag to a person, the command will be executed successfully, but the same tag will not be added to the person.
* The command result will display the total number of persons the command have successfully executed on and not the total number of persons the tags are added to.

**Format**: `tag add { shown | selected | INDEX ... } -t TAG...`

![Tag Add](images/UG_Tag%20Add%20Command.png)

**Examples**:

| Example                                     | Description                                                                  |
| ------------------------------------------- | ---------------------------------------------------------------------------- |
| `tag add shown -t Photoshop`                | Adds `Photoshop` tag to the persons shown in the UI.                          |
| `tag add selected -t Illustrator`           | Adds `Illustrator` tag to the persons selected.                               |
| `tag add 1 2 3 -t Photoshop -t Illustrator` | Adds `Photoshop` and `Illustrator` tags to persons at index `1`, `2` and `3`. |

**:bulb: Bulk Add Tag**

To bulk add tag, either do:
* `tag add 1 2 3` to add tags to persons at indexes 1, 2 and 3 or,
* `tag add shown` to add tags to all the shown persons or,
* `tag add selected` to add tags to all the selected persons

### Delete tags from persons: `tag delete`

Delete tags from persons in address book.

* A `tag delete` command deleting the `Photoshop` tag from a person without `Photoshop` tag will be executed successfully.
* When deleting a tag from a person without the tag, the command will be executed successfully, but no tags will be deleted from the person.
* The command result will display the total number of persons the command have successfully executed on and not the total number of persons the tags are deleted from.

**Format**: `tag delete { shown | selected | INDEX... } -t TAG...`

![Tag Delete](images/UG_Tag%20Delete%20Command.png)

**Examples**:

| Example                                        | Description                                                                       |
| ---------------------------------------------- | --------------------------------------------------------------------------------- |
| `tag delete shown -t Photoshop`                | Deletes `Photoshop` tag from the persons shown in the UI.                          |
| `tag delete selected -t Illustrator`           | Deletes `Illustrator` tag from the persons selected.                               |
| `tag delete 1 2 3 -t Photoshop -t Illustrator` | Deletes `Photoshop` and `Illustrator` tags from persons at index `1`, `2` and `3`. |

**:bulb: Bulk Delete Tag**

To bulk delete tag, either do:
* `tag delete 1 2 3` to delete tags to persons at indexes 1, 2 and 3 or,
* `tag delete shown` to delete tags to all the shown persons or,
* `tag delete selected` to delete tags to all the selected persons

## Saving the data

A-Bash Book data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

A-Bash Book data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-danger">

:exclamation: **Caution:**

If changes to the data file renders its format invalid, A-Bash Book will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

# FAQ

## What is the Home Folder?

The home folder is the file system folder on your computer where A-Bash Book stores your data.

```
foldername (Home Folder)
├── abashbook.jar
├── abashbook.log.0
├── config.json
├── data
│   ├── abashbook.json
│   └── alias.json
├── preferences.json
```

## How do I transfer my data to another Computer?

Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous A-Bash Book home folder.

--------------------------------------------------------------------------------------------------------------------

# Command Summary

| Action           | Format, Examples                                                                                                                                                                                                                             |
| ---------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**          | `add -n NAME -p PHONE_NUMBER -e EMAIL -c COMPANY -j JOB_TITLE -a ADDRESS [-r REMARK] [-t TAG]...` <br> e.g., `add -n John Doe -p 98765432 -e johnd@example.com -c Google -j HR Manager -a John street -r Likes ramen -t friend -t colleague` |
| **Clear**        | `clear`                                                                                                                                                                                                                                      |
| **Delete**       | `delete { shown | selected | INDEX... }` <br> e.g., `delete 3`                                                                                                                                                                               |
| **Edit**         | `edit { shown | selected | INDEX... } [-n NAME] [-p PHONE] [-c COMPANY] [-j JOB_TITLE] [-e EMAIL] [-a ADDRESS] [-r REMARK] [-t TAG]...` <br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`                                              |
| **Find**         | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find James Jake`                                                                                                                                                                                  |
| **Filter**       | `filter [-OPTION]...` <br> e.g., `filter -p -a` to see only the phone number and address                                                                                                                                                     |
| **Select**       | `select { clear | shown | show | INDEX... }`                                                                                                                                                                                                 |
| **Email**        | `email { shown | selected | INDEX... }`                                                                                                                                                                                                      |
| **List**         | `list`                                                                                                                                                                                                                                       |
| **Help**         | `help`                                                                                                                                                                                                                                       |
| **Add Alias**    | `alias add [ALIAS] [COMMAND]`<br> e.g. `alias add ls list`                                                                                                                                                                                   |
| **Delete Alias** | `alias delete [ALIAS]`<br> e.g. `alias delete ls`                                                                                                                                                                                            |
| **List Alias**   | `alias list`                                                                                                                                                                                                                                 |
| **Add Tag**      | `tag add { shown | selected | INDEX... } -t TAG...` <br> e.g., `tag add shown -t Illustrator`                                                                                                                                   |
| **Delete Tag**   | `tag delete { shown | selected | INDEX... } -t TAG...` <br> e.g., `tag delete 1 2 3 -t Photoshop -t Illustrator`                                                                                                                                         |

# Field Summary

| Mandatory | Field        | Command Flag | Restrictions                                                                                                                                                                                                                                                                                                                                                                                                          |
| --------- | ------------ | ------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Yes       | Name         | `-n`         | Names should only contain alphanumeric characters and spaces, and it should not be blank                                                                                                                                                                                                                                                                                                                              |
| Yes       | Email        | `-e`         | Emails should be of the format local-part@domain and adhere to the following constraints:<br>1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (!#$%&'*+/=?`{|}~^.-) `.<br>2. This is followed by a '@' and then a domain name. The domain name must:<br>    - be at least 2 characters long<br>    - start and end with alphanumeric characters |
| Yes       | Company      | `-c`         | Company can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                               |
| Yes       | Job Title    | `-j`         | Job Title can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                             |
| Yes       | Address      | `-a`         | Addresses can take any values, and it should not be blank                                                                                                                                                                                                                                                                                                                                                             |
| Yes       | Phone Number | `-p`         | Phone numbers should only contain numbers, and it should be at least 3 digits long                                                                                                                                                                                                                                                                                                                                    |
| No        | Tag          | `-t`         | Tags names should be alphanumeric and contains no spaces or symbols                                                                                                                                                                                                                                                                                                                                                   |
| No        | Remark       | `-r`         | None                                                                                                                                                                                                                                                                                                                                                                                                                  |

# Glossary

| Term     | Explanation                                                                      |
| -------- | -------------------------------------------------------------------------------- |
| Bash     | The well known terminal interpreter                                              |
| CLI      | Command Line Interface. A text-based interface in which commands can be entered. |
| GUI      | Graphical User Interface                                                         |
| JSON     | JavaScript Object Notation, data storage format                                  |
| Terminal | The Command Line Interface where text-based commands are entered.                |
| Case-insensitive | Uppercase `ABC` and lowercase `abc` letters are treated as being the same. <br> - `Alex Yeoh` and `alex yeoh` are treated as the same. <br> - `Photoshop` and `photoshop` are treated as the same. | 
