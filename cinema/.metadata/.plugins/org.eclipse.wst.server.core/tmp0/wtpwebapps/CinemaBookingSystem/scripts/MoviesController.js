(function(angular) {
  'use strict';
var myApp = angular.module('cinemaManager', []);
myApp.factory('moviesService', function() {
    return {
      getMovies: function() {
        var movies=[
        {
          name:"Me Before You",
          year: 2016,
          image: "pictures/me_before_you.jpg",
          summary: "Louisa Clark must find a new job after being laid off from a café. She finds work in taking care of Will Traynor, a cynical banker who was completely paralyzed in by a motorcycle accident. At first, he reacts coldly to her spunkiness, but they soon become friends and develop feelings for each other, even though Louisa has an unthoughtful, marathon-running boyfriend named Patrick. Louisa learns that Will has given his parents six months before they must bring him to Switzerland for euthanasia. Will cannot deal with the pain and suffering of his disability. Louisa secretly makes it her mission to change his mind and takes him on all the adventures that she can to prove that life is worth living. However, at their final trip to Mauritius with Will's nurse Nathan, Will confesses he intends to follow through with the euthanasia and asks for her to accompany him. She is heartbroken and does not speak to him the rest of the trip. After they arrive home, she goes to Switzerland to see Will in his final moments. After his death, he bequeaths her enough money her to continue her education and instructs her to, 'live well'.",
          actors: "Emilia Clarke, Sam Claflin, Janet McTeer, Charles Dance, Brendan Coyle",
          projection: {date: "25.06.2016", hour: "20:00", hall: "pink"},
        },
        {
          name:"Finding Dory",
          year: 2016,
          image: "pictures/finding_dori.jpg",
          summary: "'Finding Dory' reunites Dory with friends Nemo and Marlin on a search for answers about her past. What can she remember? Who are her parents? And where did she learn to speak to whales",
          actors: "Ellen DeGeneres, Ed O'Neill, Ty Burrell, Kaitlin Olson, Albert Brooks, Diane Keaton, Eugene Levy, Hayden Rolence, Michael Sheen", 
          projection: {date: "25.06.2016", hour: "18:00", hall: "blue"},
        },
        {
          name:"Ice Age 5: Collision Course",
          year: 2016,
          image: "pictures/ice_age.jpg",
          summary: "Scrat's epic pursuit of his elusive acorn catapults him outside of Earth, where he accidentally sets off a series of cosmic events that transform and threaten the planet. To save themselves from peril, Manny, Sid, Diego, and the rest of the herd leave their home and embark on a quest full of thrills and spills, highs and lows, laughter and adventure while traveling to exotic new lands and encountering a host of colorful new characters.",
          actors: "Ray Romano, John Leguizamo, Denis Leary, Simon Pegg, Jennifer Lopez, Queen Latifah", 
          projection: {date: "25.06.2016", hour: "17:00", hall: "green"},
        },
        {
          name:"Now You See Me 2",
          year: 2016,
          image: "pictures/now_you_see_me.jpg",
          summary: "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface in Now You See Me 2 only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.",
          actors: "Jesse Eisenberg, Mark Ruffalo, Woody Harrelson, Dave Franco, Daniel Radcliffe, Lizzy Caplan, Jay Chou, Sanaa Lathan, Michael Caine, Morgan Freeman", 
          projection: {date: "25.06.2016", hour: "11:00", hall: "red"},
        },
        {
          name:"Suicide Squad",
          year: 2016,
          image: "pictures/suiside_squad.jpg",
          summary: "It feels good to be bad...Assemble a team of the world's most dangerous, incarcerated Super Villains, provide them with the most powerful arsenal at the government's disposal, and send them off on a mission to defeat an enigmatic, insuperable entity. U.S. intelligence officer Amanda Waller has determined only a secretly convened group of disparate, despicable individuals with next to nothing to lose will do. However, once they realize they weren't picked to succeed but chosen for their patent culpability when they inevitably fail, will the Suicide Squad resolve to die trying, or decide it's every man for himself?",
          actors: "Will Smith, Jared Leto, Margot Robbie, Joel Kinnaman, Viola Davis, Jai Courtney, Jay Hernandez, Adewale Akinnuoye-Agbaje, Ike Barinholtz, Scott Eastwood, Cara Delevingne", 
          projection: {date: "25.06.2016", hour: "19:00", hall: "yellow"},
        },]
        return movies;
      }
    };
  });
myApp.controller('MoviesController', ['$scope', 'moviesService', function($scope, moviesService) {
  $scope.timeOfDay = 'morning';
  $scope.movies = moviesService.getMovies();
}]);
myApp.directive('navigationBar', function() {
    return {
      templateUrl: 'views/NavBarView.html'
    };
  });

})(window.angular);