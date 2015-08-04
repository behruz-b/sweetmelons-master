angular.module('myApp.controllers')
.controller 'QuestionsCtrl', class
    constructor: ($scope, @$log, Questions) ->
        $scope.questions = []

        $scope.getQuestions = () =>
            Questions.query((data) =>
                if data
                    $scope.questions = data
                    @$log.info($scope.questions)
            ).$promise

        $scope.getQuestions()