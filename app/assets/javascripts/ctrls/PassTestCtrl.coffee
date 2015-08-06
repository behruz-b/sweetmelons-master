angular.module('myApp.controllers')
.controller 'PassTestCtrl', class
    constructor: ($scope, @$log, $state, $stateParams, $localForage, Questions) ->
        $scope.questions = {}
        $scope.question = {}
        $scope.index = (Number) $stateParams.questId
        $scope.quest = {
            rans:''
        }

        $scope.answer = {}

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
            userId = {}
            $localForage.getItem('userm').then (data) ->
                userId = data.id
                $log.info("store")
                $log.info(userId)
                $log.info("storage")

            $scope.answer = {
                questionId: $scope.question.id
                remainingTime: 10000
                userId: userId
            }

