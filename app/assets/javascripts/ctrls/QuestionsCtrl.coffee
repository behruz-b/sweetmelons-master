angular.module('myApp')
.controller 'HeaderCtrl', class
  constructor: ($log, $scope, $state) ->
    vm = @

#    vm.logout = ->
#      HeaderSvc.logout()
#      .then () ->
#        $state.go('root.home')