'use strict';

let crawler = require('./../services').crawler;
let func = require('./../configs/func');

let main = module.exports = {};

main.getData = async (req, res, next) => {
    let params = req.body;
    let datas = {};
    if (params.url === undefined || params.url === '') {
        datas = null;
    } else {
        datas = await crawler.crawlerDetail(params.url);
    }
    req.result = {'record': datas};
    res.send(req.result);
};

