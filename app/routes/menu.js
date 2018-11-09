'user strict';

let router = require('express').Router();
let menu = require('./../controllers').menu;

// Public routes
router.get('/', menu.get_menu);

module.exports = router;
