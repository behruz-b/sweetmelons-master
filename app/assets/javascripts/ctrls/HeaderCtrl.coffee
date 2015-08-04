angular.module('myApp')
.controller 'HeaderCtrl', class
  constructor: ($log, $scope, $state, Users) ->
    vm = @

    vm.signout = () =>
        Users.signOut((data) =>
            $state.go('root.home')
        ).$promise
