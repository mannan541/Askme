const mysql = require('mysql');

const databaseConfig = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'askme',
};

const connection = mysql.createConnection(databaseConfig);

const MYSQL_USERS_TABLE = `CREATE TABLE IF NOT EXISTS users(
    id INTEGER NOT NULL AUTO_INCREMENT, 
    name TEXT NOT NULL,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    avatar TEXT ,
    address VARCHAR(50),
    status VARCHAR(50),
    active CHAR(1),
    PRIMARY KEY(id),
    UNIQUE(email, username))`;

const MYSQL_QUESTIONS_TABLE = `CREATE TABLE IF NOT EXISTS questions(
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    toUser INTEGER,
    fromUser INTEGER,
    askedDate VARCHAR(50),
    PRIMARY KEY(id),
    FOREIGN KEY(toUser) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(fromUser) REFERENCES users(id) ON DELETE CASCADE)`;
  
connection.connect(error => {
    if (error) throw error;

    console.debug("Open MySQL Connection");

    //Create users table
    connection.query(MYSQL_USERS_TABLE, (err, res) =>{
        if (err) throw err;
        console.log("Users Table Done");
    });

    //Create questions table
    connection.query(MYSQL_QUESTIONS_TABLE, (err, res) =>{
        if (err) throw err;
        console.log("Questions Table Done");
    });
});

module.exports = connection;