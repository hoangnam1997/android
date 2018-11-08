const date = require('date-and-time');
const os = require('os');
const path = require('path');
let main = module.exports = {};


//get time stmp
main.get_timestamp = (milisecond = true) => {
    if (milisecond) {
        return new Date().getTime();
    } else {
        return parseInt(new Date().getTime() / 1000);
    }
};

//get_collection_name
main.get_collection_name = (symbol) => {
    let collection_name = '';
    let symbol_lowercase = symbol.toLowerCase();
    if (symbol_lowercase.match(/.*usdt$/) !== null) {
        collection_name = symbol_lowercase.substr(0, symbol.length - 4);
    } else {
        collection_name = symbol_lowercase.substr(0, symbol.length - 3);
    }
    return collection_name;
};

//show time
main.show_time = (timestamp = '') => {
    if (timestamp === '') {
        return date.format(new Date(), 'YYYY/MM/DD HH:mm:ss');
    } else {
        return date.format(new Date(parseInt(timestamp)), 'YYYY/MM/DD HH:mm:ss');
    }
};
//show time utc
main.show_time_utc = (timestamp = '') => {
    if (timestamp === '') {
        return date.format(new Date(), 'YYYY/MM/DD HH:mm:ss', true);
    } else {
        return date.format(new Date(parseInt(timestamp)), 'YYYY/MM/DD HH:mm:ss', true);
    }
};
//show time utc +7
main.show_time_7 = (timestamp = '') => {
    if (timestamp === '') {
        let now = new Date();
        let utc_now = new Date(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate(), now.getUTCHours(), now.getUTCMinutes(), now.getUTCSeconds(), now.getUTCMilliseconds());
        let timestamp = utc_now.getTime() + 7 * 60 * 60 * 1000;
        return date.format(new Date(timestamp), 'YYYY/MM/DD HH:mm:ss');
    } else {
        let now = new Date(parseInt(timestamp));
        let utc_now = new Date(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate(), now.getUTCHours(), now.getUTCMinutes(), now.getUTCSeconds(), now.getUTCMilliseconds());
        let timestamp = utc_now.getTime() + 7 * 60 * 60 * 1000;
        return date.format(new Date(timestamp), 'YYYY/MM/DD HH:mm:ss');
    }
};

//get UTC
main.func_get_time_UTC = (timestamp = '') => {
    let now;
    if (timestamp === '') {
        now = new Date();
    } else {
        now = new Date(parseInt(timestamp));
    }
    let hour_utc = now.getUTCHours();
    let minute_utc = now.getUTCMinutes();
    let second_utc = now.getUTCSeconds();
    let milisecond_utc = now.getUTCMilliseconds();
    return {
        hour: parseInt(hour_utc),
        minute: parseInt(minute_utc),
        second: parseInt(second_utc),
        milisecond: parseInt(milisecond_utc)
    };
};

//conver H:m:s
main.secondsToHms = (d) => {
    d = Number(d);
    let h = Math.floor(d / 3600);
    let m = Math.floor(d % 3600 / 60);
    let s = Math.floor(d % 3600 % 60);
    h = h < 10 ? '0' + h : h;
    m = m < 10 ? '0' + m : m;
    s = s < 10 ? '0' + s : s;
    return h + ':' + m + ':' + s;
};

main.prepare_json = (data) => {
    data = JSON.stringify(data);
    data = JSON.parse(data);
    return data;
};

//get full params date
main.get_date = (timestamp = '') => {
    let d;
    if (timestamp == '') {
        d = new Date();
    } else {
        d = new Date(parseInt(timestamp));
    }
    let obj = {};
    obj.fullYear = parseInt(d.getFullYear());
    obj.month = parseInt(d.getMonth());
    obj.date = parseInt(d.getDate());
    obj.hours = parseInt(d.getHours());
    obj.minutes = parseInt(d.getMinutes());
    obj.seconds = parseInt(d.getSeconds());
    obj.milliseconds = parseInt(d.getMilliseconds());
    obj.time = parseInt(d.getTime());
    obj.day = parseInt(d.getDay());

    obj.dateUTC = parseInt(d.getUTCDate());
    obj.dayUTC = parseInt(d.getUTCDay());
    obj.fullYearUTC = parseInt(d.getUTCFullYear());
    obj.hoursUTC = parseInt(d.getUTCHours());
    obj.millisecondsUTC = parseInt(d.getUTCMilliseconds());
    obj.minutesUTC = parseInt(d.getUTCMinutes());
    obj.monthUTC = parseInt(d.getUTCMonth());
    obj.secondsUTC = parseInt(d.getUTCSeconds());

    obj.day_utc_of_month = parseInt(d.getUTCDate());
    obj.day_utc_of_week = parseInt(d.getUTCDay());

    obj.day_of_month = parseInt(d.getDate());
    obj.day_of_week = parseInt(d.getDay());
    return obj;
};

//get file name
main.get_file_name = (__filename) => {
    return path.basename(__filename);
};

//length obj
main.get_length_obj = (objs) => {
    if (objs === null || objs === undefined)
        return 0;
    return Object.keys(objs).length;
};

//show key obj
main.get_key_objs = (objs) => {
    if (objs === null || objs === undefined)
        return objs;
    return Object.keys(objs);
};

//compare config
main.compare_candle_and_config = (candle, config, param) => {
    let is_buy = false;
    candle = parseFloat(candle);
    config = parseFloat(config);
    switch (param) {
        case 'gt':
            if (candle > config) {
                is_buy = true;
            }
            break;
        case 'gte':
            if (candle >= config) {
                is_buy = true;
            }
            break;
        case 'eq':
            if (candle === config) {
                is_buy = true;
            }
            break;
        case 'lt':
            if (candle < config) {
                is_buy = true;
            }
            break;
        case 'lte':
            if (candle <= config) {
                is_buy = true;
            }
            break;
        case 'ne':
            if (candle !== config) {
                is_buy = true;
            }
            break;
    }
    return is_buy;
};

main.percent = async (params_1, params_2) => {
    params_1 = parseFloat(params_1);
    params_2 = parseFloat(params_2);
    if (params_2 == 0) {
        return 0;
    }
    if (params_1 === params_2) {
        return 0;
    }
    return (params_1 - params_2) / params_2 * 100;
};
main.copy_json = (src) => {
    if (src == null || src == undefined)
        return src;
    return JSON.parse(JSON.stringify(src));
};

main.reverse_string = (str) => {
    return str
        .split('')
        .reduce((ret, character) => (character + ret));
};

main.get_ip_address = () => {
    let ifaces = os.networkInterfaces();
    let ipAddress = ifaces['eth0'] != undefined ? ifaces['eth0'][0]['address'] : '127.0.0.1';
    return ipAddress;
};

//check coin BTC
main.get_main_coin = (symbol) => {
    symbol = symbol.toUpperCase();
    return (symbol.substr(symbol.length - 4, symbol.length) !== 'USDT') ? symbol.substr(symbol.length - 3, symbol.length) : 'USDT';
};

main.first_uppercase = (string) => {
    return string.charAt(0).toUpperCase() + string.slice(1);
};

//check coin BTC
main.get_name_coin = (symbol) => {
    symbol = symbol.toUpperCase();
    return (symbol.substr(symbol.length - 4, symbol.length) !== 'USDT') ? symbol.substr(0, symbol.length - 3) : symbol.substr(0, symbol.length - 4);
};

main.get_timestamp_today_utc = (day = '', month = '', year = '') => {
    if (day === '') {
        let obj_time = main.get_date();
        day = obj_time.day_utc_of_month;
        month = obj_time.monthUTC;
        year = obj_time.fullYearUTC;
    }
    let obj_date = new Date(Date.UTC(year, month, day));
    return parseInt(obj_date.getTime() / 1000);
};

//remove_utf8
main.remove_utf8 = (alias) => {
    var str = alias;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, 'a');
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, 'e');
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, 'i');
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, 'o');
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, 'u');
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, 'y');
    str = str.replace(/đ/g, 'd');
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|\"|\&|\#|\[|\]|~|\$|-|_|`|{|}|\||\\|\s/g, '-');
    str = str.replace(/-+/g, '-');
    str = str.trim();
    return str;
};