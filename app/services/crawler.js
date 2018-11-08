let main = module.exports = {};

const log = console.log.bind(console);
const func = require('./../configs/func');

var Crawler = require('crawler');

main.run = async () => {

};


main.get_menu = async () => {
    return new Promise(async resolve => {
        let datas = [];
        var c = new Crawler({
            maxConnections: 10,
            // This will be called for each crawled page
            callback: function (error, res, done) {
                if (error) {
                    log(error);
                } else {
                    var $ = res.$;
                    // $ is Cheerio by default
                    //a lean implementation of core jQuery designed specifically for the server
                    let promises = $('.p_menu a').each((index, ele) => {
                        let a_herf = $(ele).attr('href');
                        let text = $(ele).text();
                        a_herf = a_herf.replace('//', '/');
                        if (a_herf.length > 5) {
                            if (a_herf.match(/.*vnexpress.net/) === null) {
                                a_herf = 'https://vnexpress.net' + a_herf;
                            } else {
                                let pos = a_herf.indexOf('//');
                                a_herf = 'https://' + a_herf.slice(pos + 2);
                            }
                            let obj = {
                                'url': a_herf,
                                'text': text.trim()
                            };
                            datas.push(obj);
                        }
                    });
                    Promise.all([promises]);
                    resolve(datas);
                }
                done();
            }
        });

        // Queue just one URL, with default callback
        c.queue('https://vnexpress.net');
    });
};

main.crawlerData = async (url = 'https://vnexpress.net') => {
    let $ = await main.crawlerApi(url);
    let datas = [];
    let promises = $('.container  article.list_news').each((index, ele) => {
        let url = $(ele).find('.title_news a:first-child').attr('href');
        let title = $(ele).find('.title_news a:first-child').attr('title');
        let thumb_art = $(ele).find('.thumb_art a:first-child img').attr('data-original');
        let description = $(ele).find('.description').text().trim();
        if (description !== '') {
            datas.push({
                url: url,
                title: title,
                thumb_art: thumb_art,
                description: description,
            });
        }
    });
    Promise.all([promises]);
    return datas;
};

main.crawlerApi = (url = 'https://vnexpress.net') => {
    return new Promise(async resolve => {
        var c = new Crawler({
            maxConnections: 10,
            // This will be called for each crawled page
            callback: function (error, res, done) {
                if (error) {
                    log(error);
                } else {
                    var $ = res.$;
                    resolve($);
                }
                done();
            }
        });
        // Queue just one URL, with default callback
        c.queue(url);
    });
};