# NotesRestApplication
Welcome to Notes Rest Application.
It is a Spring Boot Rest application for writing, 
viewing, editing and deleting notes.

## Starting

There are several ways to start the application.

```bash
The first one is to download the raw files or to clone 
the whole respository. You also need JDK 15 or newer.

After that you start the main method from
NotesRestApplication.class 
```

```bash
The second way is by starting the NotesRestApplication-0.0.1-SNAPSHOT.jar
 file from "target" folder. You also need JDK 15 or newer.

You can do that by typing 
java -jar target/NotesRestApplication-0.0.1-SNAPSHOT.jar
into the terminal.
```
```bash
The third way if you do not want to download JDK, you can use 
Docker by downloading and run the image from: 
https://hub.docker.com/repository/docker/alexandar99naydenov/notes_rest_application
```

## Usage

```python
After you start the application you can :

 - Adding a Note 

With HTTP POST request to localhost:12345/notes
        with Body with JSON Note format 
        example : {"author":"Author of the note", 
                   "text" : "Text of the note"}

- View all notes

With HTTP GET request to localhost:12345/notes

- View specific note by ID

With HTTP GET request to localhost:12345/notes/id

- Change a note

With HTTP PUT request to localhost:12345/notes/id
        with Body the new text you want to put (String format)
        example : This will be the new text of the note

- Deleting specific note by ID

With HTTP REMOVE request to localhost:12345/notes/id

- Deleting all notes

With HTTP GET request to localhost:12345/notes/deleteAll

- Finding all notes from specific author

With HTTP GET request to localhost:12345/notes/author/{authorName}
where in place of {authorName} you should place the name of the author
```

## Contributing
Alexandar Naydenov
