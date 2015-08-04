angular.module('myApp')
.config ($stateProvider, $urlRouterProvider, $httpProvider) ->

  $urlRouterProvider.otherwise '/home'
  $httpProvider.defaults.useXDomain = true
  $httpProvider.defaults.withCredentials = true
  delete $httpProvider.defaults.headers.common['X-Requested-With']

  $stateProvider.state("root",
    url: ""
    abstract: true
    views:
      header:
        templateUrl: "/assets/partials/header.tpl.html"
        controller: "HeaderCtrl"
        controllerAs: "vm"
      footer:
        templateUrl: "/assets/partials/footer.tpl.html"
  )

  $stateProvider.state("root.home",
    url: "/home"
    views:
      "main@":
        templateUrl: "assets/partials/main.tpl.html"
    data:
      pageTitle: 'Home'
  )

  $stateProvider.state("root.users",
    url: "/users"
    views:
      'main@':
        controller: "UsersCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/users.html"
    data:
      pageTitle: 'Users'
  )

  $stateProvider.state("root.quetions",
      url: "/questions/add"
    views:
      'main@':
        controller: "QuestionsCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/add-question.html"
      'header@':
        templateUrl: 'assets/partials/admin-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
      'footer@':
        templateUrl: 'assets/partials/admin-footer.tpl.html'
    data:
      pageTitle: 'Adminstration Page'
  )

  $stateProvider.state("root.questedit",
    url: "/questions/edit"
    views:
      'main@':
        controller: "QuestionEditCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/add-question.html"
      'header@':
        templateUrl: 'assets/partials/admin-header.tpl.html'
        controller: 'HeaderCtrl'
        controllerAs: 'vm'
    data:
      pageTitle: 'Adminstration Page'
  )