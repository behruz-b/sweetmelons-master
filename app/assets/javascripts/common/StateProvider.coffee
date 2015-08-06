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
    url: "/users/list"
    views:
      'main@':
        controller: "UserCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/users.html"
    data:
      pageTitle: 'Users'
  )
  $stateProvider.state("root.results",
    url: "/users/answers"
    views:
      'main@':
        controller: "ResultCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/results.html"
    data:
      pageTitle: 'Users'
  )
  $stateProvider.state("root.signup",
    url: "/signup"
    views:
      'main@':
        controller: "SignUpCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/signup.html"
    data:
      pageTitle: 'Users'
  )
  $stateProvider.state("root.signin",
    url: "/signin"
    views:
      'main@':
        controller: "SignInCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/signin.html"
      'header@':
        templateUrl: 'assets/partials/header-login.tpl.html'
    data:
      pageTitle: 'Users'
  )
  $stateProvider.state("root.questions",
    url: "/questions/list"
    views:
      'main@':
        controller: "QuestionsCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/questions.html"
    data:
      pageTitle: 'Adminstration Page'
  )
  $stateProvider.state("root.questadd",
    url: "/questadd"
    views:
      'main@':
        controller: "AddQuestionCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/add-question.html"
    data:
      pageTitle: 'Adminstration Page'
  )
  $stateProvider.state("root.questedit",
    url: "/questions/edit"
    views:
      'main@':
        controller: "QuestionEditCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/question.html"
    data:
      pageTitle: 'Adminstration Page'
  )
  $stateProvider.state("root.start",
    url: "/tests/:questId"
    views:
      'main@':
        controller: "PassTestCtrl"
        controllerAs: "vm"
        templateUrl: "assets/partials/passing-test.html"
    data:
      pageTitle: 'Adminstration Page'
  )