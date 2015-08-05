angular.module('myApp.controllers')
.controller 'PassTestCtrl', class
    constructor: ($scope, @$log, $state, $stateParams, Questions) ->
        $scope.questions = {}
        $scope.question = {}
        $scope.index = (Number) $stateParams.questId
        $scope.quest = {
            rans:''
        }

        vm = @

        $scope.getQuestions = () =>
            Questions.tests((data) =>
                if data
                    $scope.questions = data
                    $scope.question = $scope.questions[$scope.index]
            ).$promise

        $scope.getQuestions()

        $scope.saveAnswer = () =>
            $scope.index += 1
            @$log.info($scope.index)
            @$log.info($scope.questions[$scope.index])
            $scope.question = $scope.questions[$scope.index]


