const mongoose = require('mongoose');
const configsDatabase = require('./database');
const autoIncrement = require('mongoose-auto-increment');

const func = require('./func');
const os = require('os');
const log = console.log.bind(console);
let ifaces = os.networkInterfaces();
let ipAddress = ifaces['eth0'] === undefined ? '127.0.0.1' : ifaces['eth0'][0]['address'];

//connect database
mongoose.connect(configsDatabase.database, {useNewUrlParser: true});
mongoose.set('useCreateIndex', true);
mongoose.Promise = global.Promise;
let db = mongoose.connection;
db.on('error', () => {
    log('Mongo' + ipAddress + ' die');
});
autoIncrement.initialize(db);
module.exports = mongoose;
