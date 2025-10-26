# Simple Dictionary App (Java + SQLite)

A simple Java console application that allows users to search for words in a dictionary stored in an SQLite database
## Features

- Search for a word and get its:
  - Word Type (noun, verb, etc.)
  - Definition
- Uses SQLite database to store dictionary entries.
- Simple, lightweight, and easy to use.

## Project Structure

├── bin/
├── src/
│ ├── Main.java # Entry point of the app and holds all the logic                  
├── lib/
│ └── sqlite-jdbc-3.50.3.0.jar # SQLite connector                                 
├──dictionary.db # Local SQLite database                           
├── README.md                                       
└── .gitignore


## Prerequisites

- Java JDK 8 or higher
- Optional: DB Browser for SQLite (to view/edit database entries)

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/your-username/DictionaryApp.git


#Navigate to the project folder:
cd DictionaryApp 
# run using:
javac -cp "lib/sqlite-jdbc-3.50.3.0.jar" src/Main.java
java -cp "lib/sqlite-jdbc-3.50.3.0.jar;src" Main



