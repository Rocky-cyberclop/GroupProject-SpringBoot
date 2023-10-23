
var areaData = {
	labels: ["2013", "2014", "2015", "2016", "2017", "2018", "2019"],
	datasets: [{
		label: 'Doanh số',
		data: [12, 19, 3, 5, 2, 3, 20],
		backgroundColor: [
			'rgba(255, 99, 132, 0.2)'//,
			// 'rgba(54, 162, 235, 0.2)',
			// 'rgba(255, 206, 86, 0.2)',
			// 'rgba(75, 192, 192, 0.2)',
			// 'rgba(153, 102, 255, 0.2)',
			// 'rgba(255, 159, 64, 0.2)'
		],
		borderColor: [
			'rgba(255,99,132,1)'
			//   'rgba(54, 162, 235, 1)',
			//   'rgba(255, 206, 86, 1)',
			//   'rgba(75, 192, 192, 1)',
			//   'rgba(153, 102, 255, 1)',
			//   'rgba(255, 159, 64, 1)'
		],
		borderWidth: 1,
		fill: true, // 3: no fill
	}]
};

var areaOptions = {
	plugins: {
		filler: {
			propagate: true
		}
	}/*,
	maintainAspectRatio: false,*/
}
var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
var areaChart = new Chart(areaChartCanvas, {
	type: 'line',
	data: areaData,
	options: areaOptions
});

document.querySelector(".btn-statistic").addEventListener("click", function(event) {

	/*document.querySelector(".end_date")*/
	event.preventDefault()
	fetch("http://localhost:8080/api/admin/statistic/all/" +
		document.querySelector(".start-date").value + "/" +
		document.querySelector(".end-date").value, { method: "get" })
		.then(response => response.json())
		.then(json_statistic => {
			areaData.labels = json_statistic['labels']
			areaData.datasets[0].data = json_statistic['values']
			if ($("#areaChart").length) {
				areaChart.destroy()
				areaChart = new Chart(areaChartCanvas, {
					type: 'line',
					data: areaData,
					options: areaOptions
				});

			}
		})
})



