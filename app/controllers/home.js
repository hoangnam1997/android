'use strict';

let crawler = require('./../services').crawler;
let func = require('./../configs/func');

let main = module.exports = {};

main.getData = async (req, res, next) => {
    let datas = await crawler.crawlerData();
    req.result = {'record': datas};
    res.send(req.result);
};

