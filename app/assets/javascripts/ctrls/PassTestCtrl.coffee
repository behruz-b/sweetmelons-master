angular.module('myApp.controllers')
.controller 'PassTestCtrl', class
    constructor: ($scope, @$log, $state, $stateParams, $localForage, Questions) ->
        $scope.questions = {}
        $scope.question = {}
        $scope.index = (Number) $stateParams.questId
        $scope.quest = {
            rans:''
        }

        $scope.rAns = {}
        $scope.answer = {}
        $scope.answers = []

        vm = @

        $scope.getQuestions = () =>
            Questions.tests((data) =>
                if data
                    $scope.questions = data
                    $scope.question = $scope.questions[$scope.index]
                    $scope.rAns = $scope.question.rAns

            ).$promise

        $scope.getQuestions()

        $scope.saveAnswer = () =>
            $scope.index += 1
            isRightAns = if $scope.rAns in [$scope.quest.rans] then true else false
            user = {}

            $localForage.getItem('userm').then (data) ->
                user = data
                $scope.answer = {
                    questionId: $scope.question.id
                    remainingTime: 10000
                    userId: user.id
                    userName: user.firstName + " " + user.lastName
                    rAns: isRightAns
                    tAns: $scope.quest.rans
                }

                $log.info("++++++++++++++++")
                $log.info($scope.answer)
                $log.info("================")
                $scope.quest = {
                    rans:''
                }

            if ($scope.questions.length - 1 >= $scope.index)
                $scope.question = $scope.questions[$scope.index]

