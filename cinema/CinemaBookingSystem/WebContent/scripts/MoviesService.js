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
          image: "../pictures/me_before_you.jpg",
          summary: "Louisa Clark must find a new job after being laid off from a café. She finds work in taking care of Will Traynor, a cynical banker who was completely paralyzed in by a motorcycle accident. At first, he reacts coldly to her spunkiness, but they soon become friends and develop feelings for each other, even though Louisa has an unthoughtful, marathon-running boyfriend named Patrick. Louisa learns that Will has given his parents six months before they must bring him to Switzerland for euthanasia. Will cannot deal with the pain and suffering of his disability. Louisa secretly makes it her mission to change his mind and takes him on all the adventures that she can to prove that life is worth living. However, at their final trip to Mauritius with Will's nurse Nathan, Will confesses he intends to follow through with the euthanasia and asks for her to accompany him. She is heartbroken and does not speak to him the rest of the trip. After they arrive home, she goes to Switzerland to see Will in his final moments. After his death, he bequeaths her enough money her to continue her education and instructs her to, 'live well'.",
          actors: "Emilia Clarke, Sam Claflin, Janet McTeer, Charles Dance, Brendan Coyle",
          projection: {date: "25.06.2016", hour: "20:00", hall: "Sofia"},
        }]
        return movies;
      }
    };
  });
})(window.angular);