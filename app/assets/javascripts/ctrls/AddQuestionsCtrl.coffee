angular.module('myApp.controllers')
.controller 'AddQuestionsCtrl', class
    constructor: ($log, $scope, $state, Questions) ->
        vm = @
        glob = $scope.Glob
        vm.questions = {}

        vm.getQuestions = () =>
            Questions.list((data) =>
                $log.info(data)
                if data
                    vm.ques = data
                    $log.info(vm.hotels)
            ).$promise


        vm.showQuestions = () ->
            $state.go('root.questions')
        vm

