'user strict';

let main = module.exports = {};
let menuModels = require('./schema/menus');
let func = require('./../configs/func');

main.saveMenu = async (datas) => {
    return new Promise(resolve => {
        datas.forEach((obj, index) => {
            let where = {
                slug: func.remove_utf8(obj['text']),
            };
            let update = {
                title: obj['text'],
                slug: func.remove_utf8(obj['text']),
                url: obj['url']
            };
            menuModels.findOne(where, (err, docs) => {
                if (docs === null) {
                    new menuModels(update).save();
                } else {
                    menuModels.updateOne(where, update, {upsert: true}, (err, docs) => {
                    });
                }
            });
            resolve({'status': 'success'});
        });
    });
};

main.getMenu = async () => {
    return new Promise(resolve => {
        let result = {};
        menuModels.find({}, {_id: 0}, (err, docs) => {
            if (docs.length === 0) {
                resolve({'datas': {}});
            }
            resolve({'datas': {'record': docs}});
        });
    });
};


