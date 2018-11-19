'user strict';

let router = require('express').Router();
let detail = require('./../controllers').detail;

// Public routes
router.post('/', detail.getData);

module.exports = router;
