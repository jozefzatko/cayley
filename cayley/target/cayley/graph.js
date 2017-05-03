$(function drawGraph(n) {

	var n = location.search.split('n=')[1]
	var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/cayley/api/graph/".concat(n), false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var elem = JSON.parse(xhttp.responseText);
	
	var cy = cytoscape({

		container: document.getElementById('cy'),

		layout: { name: 'circle' },
		
		boxSelectionEnabled: false,
		autounselectify: true,

		wheelSensitivity: 0.05,
		
		style: cytoscape.stylesheet()
			.selector('node')
				.css({
					'content': 'data(id)',
					'height': 40,
		            'width': 40,
		            'background-color': '#0000ff'
				})
			.selector('edge')
				.css({
					'width': 2,
					"font-size": 10,
					'opacity': 0.8,
					'line-color': 'data(color)',
					'target-arrow-color': 'data(color)',
					'curve-style': 'bezier'
				})
			.selector('.highlighted')
				.css({
					'background-color': '#61bffc',
					'label': 'data(label)',
					'line-color': '#61bffc',
					'transition-property': 'background-color, line-color',
					'transition-duration': '0.5s'
				}),
		
		elements: elem
	});

	var bfs = cy.elements().bfs('#a', function(){}, true);

	var i = 0;
	var highlightNextEle = function(){
		if( i < bfs.path.length ){
			bfs.path[i].addClass('highlighted');  
			i++;
			setTimeout(highlightNextEle, 1000);
		}
	};

	highlightNextEle();

});
