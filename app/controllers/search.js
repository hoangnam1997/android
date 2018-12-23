'use strict';

let crawler = require('./../services').crawler;
let func = require('./../configs/func');

let main = module.exports = {};

main.getData = async (req, res, next) => {
    let params = req._parsedUrl.query.split('=');
    let textSearch = params[1];
    let datas = null;
    if (textSearch === undefined || textSearch === '') {
        datas = null;
    } else {
        textSearch = textSearch === undefined || textSearch === '' ? 'bong+da' : textSearch;
        //textSearch = "bong+da";
        let url = 'https://timkiem.vnexpress.net/?search_f=title&q=' + textSearch + '&cate_code=&media_type=all&latest=&fromdate=&todate=&date_format=all';
        datas = await crawler.crawlerData(url);
    }
    req.result = {'record': datas};
    res.send(req.result);
};

