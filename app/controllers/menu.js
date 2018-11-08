'use strict';

let crawler = require('./../services').crawler;
let menuModel = require('./../models').menu;
let func = require('./../configs/func');

let main = module.exports = {};

main.get_menu = async (req, res, next) => {
    let datas = await menuModel.getMenu();
    if (func.get_length_obj(datas['datas']) === 0) {
        datas = await crawler.get_menu();
        menuModel.saveMenu(datas);
    } else {
        datas = datas['datas'];
    }
    req.result = datas;
    res.send(req.result);

};
main.get_id = async (req, res, next) => {
    let params = req.params;
    let id = params['id'];
    let item = await menuModel.getItemId(id);
    let datas = [];
    if (item !== null) {
        datas = await crawler.crawlerData(item['url']);
    }

    req.result = {'record': datas};
    res.send(req.result);

};
