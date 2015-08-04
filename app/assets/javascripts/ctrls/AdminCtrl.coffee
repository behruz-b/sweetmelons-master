angular.module('myApp')
.controller 'AdminCtrl', class
  ($scope, $http) ->
    vm = @
    vm.users = []
    $scope.loadingFiles = true
    $http.get('/questions/list').then ((response) ->
      $scope.loadingFiles = false
      $scope.queue = response.data.files or []
      return
    ), ->
      $scope.loadingFiles = false

    $scope.getUsers = () =>
      Users.findUsers((data) =>
        $log.info(data)
        if data
          vm.users = data
          $log.info(vm.users)
      ).$promise



