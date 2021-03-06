<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Issue Tracking System</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.3/dialog-polyfill.css">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.blue_grey-orange.min.css">
    <link rel="stylesheet" href="/static/styles.css">
    <link rel="stylesheet" href="/static/tinymce.css">
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
        <div class="issues__posts mdl-grid">
          <div class="issues-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title mdl-shadow--2dp" style="height:80px;">
              <h2 class="mdl-card__title-text mdl-color-text--grey-800">Milestones</h2>
            </div>
            <a href="/newMilestone" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored show-modal">
              <i class="material-icons">add</i>
            </a>
            <div class="mdl-card__supporting-text">
              <ul class="issue-list mdl-list">
              
              <c:forEach items="${milestones}"  var="milestone">
                <li class="mdl-list__item mdl-list__item--two-line">
                  <span class="mdl-list__item-primary-content">
                    <a href="/milestone/${milestone.id}"><strong>${milestone.subject}</strong></a>
                    <span class="mdl-list__item-sub-title">
                      Due by June 23, 2016
                    </span>
                  </span>

                  <span class="mdl-list__item-secondary-content">
                    <span class="progress-bar">
                      <span class="progress" style="width:${milestone.openIssuesNum / milestone.issuesNum * 300}px"> </span>
                    </span>
                    <span class="mdl-list__item-sub-title" style="font-size:13px; color:#888">
                    ${milestone.openIssuesNum} Open ${milestone.issuesNum - milestone.openIssuesNum} Closed
                    </span>
                  </span>
                </li>
               </c:forEach>
               
              </ul>
            </div>
          </div>
        </div>
        <footer class="mdl-mini-footer">
          <div class="mdl-mini-footer--left-section">
            <div class="mdl-logo mdl-color-text--grey-600">Proudly powered by <a href="https://github.com/Byeol">Byeol</a></div>
          </div>
        </footer>
      </main>
      <div class="mdl-layout__obfuscator"></div>
    </div>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.3/dialog-polyfill.min.js"></script>
    <script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
    <script src="main.js"></script>
  </body>
</html>
