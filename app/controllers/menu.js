'use strict';

let crawler = require('./../services').crawler;
let menuModel = require('./../models').menu;
let func = require('./../configs/func');

let main = module.exports = {};

main.get_menu = async (req, res, next) => {
    let datas;
    if (req._parsedUrl.query === null) {
        datas = await menuModel.getMenu();
        if (func.get_length_obj(datas['datas']) === 0) {
            datas = await crawler.get_menu();
            menuModel.saveMenu(datas);
        } else {
            datas = datas['datas'];
        }
    } else {
        datas = await main.get_id(req._parsedUrl.query);
    }

    req.result = {record: datas};
    res.send(req.result);

};
main.get_id = async (query) => {
    let id = query.split('=')[1];
    let item = await menuModel.getItemId(id);
    let datas = [];
    if (item !== null) {
        datas = await crawler.crawlerData(item['url']);
    }
    return datas;

};
