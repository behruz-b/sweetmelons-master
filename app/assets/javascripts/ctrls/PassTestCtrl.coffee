angular.module('myApp.controllers')
.controller 'PassTestCtrl', class
    constructor: ($scope, $log, $location, $state, $stateParams, PassTest) ->
        vm = @
        glob = $scope.Glob
        vm.hotels = {}
        $scope.cityId = 1
        searchParams =
            hotelTypeId: 1
            starRating: 5
        searchParams.cityId = $stateParams.cityId
        searchParams.checkInDate = $stateParams.checkInDate
        searchParams.checkOutDate = $stateParams.checkOutDate

        vm.findHotels = () =>
            Search.findHotel(searchParams, (data) =>
                if data
                    vm.hotels = data.rows
                    $log.info(vm.hotels)
            ).$promise

        vm.showDetails = (hotelId) =>
            $state.go('root.details', hotelId)

        vm.findHotels()
        vm


