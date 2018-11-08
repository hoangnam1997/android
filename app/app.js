'use strict';

const express = require('express');
require('./configs/connectDb');
const configs_port = require('./configs/port');
const func = require('./configs/func');
const log = console.log.bind(console);

//init app
const app = express();

let bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({extended: true})); // support encoded bodies

//routers
app.use(require('./routes'));

//socket.io
const server = require('http').Server(app);

server.on('listening', function () {
    log('ok, server is running');
});
server.on('error', function (err) {
    log(err);
});
// open port on server
let port = configs_port['PORT_65014'];
server.listen(port, () => {
    log('File: %s', func.get_file_name(__filename), func.show_time());
    log(`Server started on port ${port}`);
});


app.get('/', function (req, res) {
    res.end('Hello World!');
});