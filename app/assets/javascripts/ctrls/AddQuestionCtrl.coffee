angular.module('myApp.controllers')
.controller 'AddQuestionCtrl', class
    constructor: ($log, $scope, $state, Questions) ->
        vm = @
        $scope.questions = {}

        vm.addQuestion = (question) =>
            Questions.add(question, (data) =>
                if data
                    $state.go('root.questions')
            ).$promise
