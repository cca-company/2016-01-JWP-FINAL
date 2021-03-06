<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Issue Tracking System</title>

    <link href='https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue_grey-orange.min.css">
    <link rel="stylesheet" href="/static/styles.css">
  </head>
  <body>
    <div class="issues issues--wrap mdl-layout mdl-js-layout has-drawer is-upgraded">

    <header class="mdl-layout__header mdl-layout__header--waterfall">
      <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">Issue Tracking System</span>
        <div class="mdl-layout-spacer"></div>
        <!-- Tabs -->
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="/">Issues</a>
          <a class="mdl-navigation__link" href="/milestones">Milestones</a>
          <c:choose>
            <c:when test="${sessionScope.user ne null}">
              <a class="mdl-navigation__link" href="/logout">logout</a>
            </c:when>
            <c:otherwise>
              <a class="mdl-navigation__link" href="/login">login</a>
              <a class="mdl-navigation__link" href="/join">join</a>
            </c:otherwise>
          </c:choose>
        </nav>
      </div>
    </header>

      <main class="mdl-layout__content">
        <div class="issues-back">
          <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon back-btn" title="go back" role="button">
            <i class="material-icons" role="presentation">arrow_back</i>
          </a>
        </div>
        <form action="/newMilestone" method="POST">
        <div class="issues__posts mdl-grid">
          <div class="mdl-card mdl-shadow--4dp mdl-cell mdl-cell--12-col">
            <div class="mdl-card__title mdl-color-text--grey-50" style="height:100px;">
              <i class="material-icons">check_circle</i>
              <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width:90%">
                <input class="mdl-textfield__input" type="text" id="subject" name="subject" required>
                <label class="mdl-textfield__label" for="subject">Subject</label>
              </div>
            </div>
            <div class="mdl-color-text--primary-contrast" style="padding-top:40px; padding-bottom:40px;">
              <div class="input-date" style="padding:15px;">
                <label for="startDate" style="color:#888">start date</label>
                <input type="datetime-local" name="startDate" id="startDate" required>
              </div>
              <div class="input-date" style="padding:15px;">
                <label for="endDate" style="color:#888">end date</label>
                <input type="datetime-local" name="endDate" id="endDate" required>
              </div>
            </div>
            <div class="mdl-color-text--primary-contrast mdl-card__supporting-text">
              <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" type="submit" style="margin:10px auto; width:200px;">Submit</button>
            </div>
          </div>
        </div>
        </form>
        <footer class="mdl-mini-footer">
          <div class="mdl-mini-footer--left-section">
            <div class="mdl-logo mdl-color-text--grey-600">Proudly powered by <a href="https://github.com/Byeol">Byeol</a></div>
          </div>
        </footer>
      </main>
      <div class="mdl-layout__obfuscator"></div>
    </div>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script>
      $('.back-btn').on('click',function(){history.back();})
    </script>
  </body>
</html>
