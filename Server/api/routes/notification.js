const express = require('express');
const controller = require('../controllers/notification');
const checkAuth = require('../../middleware/check_auth');
const router = express.Router();

router.get('/', controller.getAllNotifications);

router.get('/:id', controller.getNotificationByID);

router.get('/new', controller.getUnReadedNotification);

router.post('/', controller.createNewNotification);

router.delete('/', controller.deleteAllNotifications);

router.delete('/:id', controller.deleteNotificationByID);

router.put('/readed', controller.makeNotificationReaded);

module.exports = router;