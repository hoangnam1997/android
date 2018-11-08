'use strict';
let socket_io = module.exports = {};

let log = console.log.bind(console);
let request_client = require('./request_main');
let vps = require('./../../../configs/server');

socket_io.run = (io, port) => {
    io.on('connection', function (socket) {
        log('ID User: ' + socket.id);
        socket.on('join_room_default', function (data) {
            let room_name = 'join_room_default';
            socket.join(room_name);
            socket[room_name] = room_name;
            log('==================================');
            log(data);
            log(socket.adapter.rooms);
            log('==================================');
        });

        socket.on('disconnect', function () {
            log(socket.id + ' ngat ket noi!!!!!');
            log('==================================');
            log(socket.adapter.rooms);
            log('==================================');
        });

        socket.on('check_candle', (data) => {
            socket_io.check_candle(io, data, port);
        });
    });
};

socket_io.check_candle = async (io, data, port) => {
    let link = vps.vps_162 + ':' + port +'/candle/';
    let result = await request_client.run(link);
    io.sockets.in('join_room_default').emit('sv_check_candle', result);
};