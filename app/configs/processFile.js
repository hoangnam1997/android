const fs = require('fs');

module.exports = {
    readFile: (filePath, callback) => {
        fs.readFile(filePath, {
            encoding: 'utf-8'
        }, function (err, data) {
            if (!err) {
                if (data === '') {
                    callback(null, {});
                } else {
                    callback(null, JSON.parse(data));
                }
            } else {
                callback(err);
            }
        });
    },
    readFileSync: (filePath) => {
        let data = fs.readFileSync(filePath, 'utf8');
        return data;
    },
    writeFile: async (filePath, content) => {
        let content_tmp = JSON.stringify(content);
        await fs.writeFile(filePath, content_tmp, function (err) {
            if (err) {
                console.log(err);
                return false;
            }
            return true;
        });
    },
    writeFileTxt: (filePath, content) => {
        return new Promise((resolve) => {
            let content_tmp = JSON.stringify(content);
            fs.writeFile(filePath, content_tmp, function (err) {
                if (err) {
                    console.log(err);
                    resolve(false);
                }
                // console.log("The file was saved!");
                resolve(true);
            });
        });
    },
    write_file_txt: (filePath, content, cb) => {
        fs.writeFile(filePath, JSON.stringify(content), function (err) {
            if (err) {
                cb(err);
            }
            cb(null);
        });
    },
    write_file_async: (filePath, content) => {
        fs.writeFileSync(filePath, content, 'utf8');
    },
    read_file_async: (filePath) => {
        return fs.readFileSync(filePath, 'utf8');
    }
};