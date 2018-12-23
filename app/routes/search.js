'user strict';

let router = require('express').Router();
let search = require('./../controllers').search;

// Public routes
router.get('/', search.getData);

module.exports = router;
