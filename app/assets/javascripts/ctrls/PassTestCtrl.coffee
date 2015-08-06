angular.module('myApp.controllers')
.controller 'PassTestCtrl', class
    constructor: ($scope, @$log, $state, $stateParams, $localForage, Questions, Users) ->
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
                    @$log.info("aaaasss")
                    @$log.info($scope.index)
                    @$log.info($scope.index)
                    @$log.info($scope.questions[$scope.index])
                    $scope.question = $scope.questions[$scope.index]
                    $scope.rAns = $scope.question.rAns

            ).$promise

        $scope.getQuestions()

        $scope.saveAnswer = () =>
            $scope.index += 1
            isRightAns = if $scope.rAns in [$scope.quest.rans] then 1 else 0
            user = {}

            $localForage.getItem('userm').then (data) ->
                user = data
                $scope.answer = {
                    userId: user.id
                    userName: user.firstName + " " + user.lastName
                    questionId: $scope.question.id
                    isRight: isRightAns
                    remaining: 10000
                    tAns: $scope.quest.rans
                }

                Users.answ($scope.answer, (data) =>
                  if data
                      $log.info(data)
                ).$promise

                $log.info("++++++++++++++++")
                $log.info($scope.answer)
                $log.info("================")
                $scope.quest = {
                    rans:''
                }

            if ($scope.questions.length - 1 >= $scope.index)
                $scope.question = $scope.questions[$scope.index]

