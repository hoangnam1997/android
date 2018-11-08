const mongoose = require('mongoose');
const collections = require('./collections');
const autoIncrement = require('mongoose-auto-increment');
autoIncrement.initialize(mongoose.connection);

let modelSchema = mongoose.Schema({
    id: Number,
    title: String,
    slug: String,
    url: String,
}, {versionKey: false});
modelSchema.plugin(autoIncrement.plugin, {
    model: collections['COL_MENUS'],
    field: 'id',
    startAt: 1,
    incrementBy: 1
});

module.exports = mongoose.model(collections['COL_MENUS'], modelSchema);