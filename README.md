# recipe-hunter

A JavaFX-based application for users to search a recipe database by keywords, 
view web pages with recipe details, and optionally receive text messages with linke to 
user-selected recipes. Supports user registration and stores user information
in own database including their passwords digests. Optional recipe-link 
texting feature uses an external communication serivce. 

This application is currently implemented as a monolithic program that maintains 
its local user database through JDBC but can be modified to use a remote database 
without much effort. JavaFX containers are currently configured mostly programmatically, 
and it's being transitioned to define the containers through FXML files generated with
Scene Builder. 
