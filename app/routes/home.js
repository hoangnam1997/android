'user strict';

let router = require('express').Router();
let home = require('./../controllers').home;

// Public routes
router.get('/', home.getData);


module.exports = router;
