AmCharts.makeChart("chartdiv",
	{
		"type": "serial",
		"categoryField": "category",
		"colors": [
			"#CDD0C0",
			"#C0B283",
			"#eea638",
			"#a7a737",
			"#86a965",
			"#8aabb0",
			"#69c8ff",
			"#cfd27e",
			"#9d9888",
			"#916b8a",
			"#724887",
			"#7256bc"
		],
		"startDuration": 1,
		"theme": "black",
		"categoryAxis": {
			"gridPosition": "start"
		},
		"trendLines": [],
		"graphs": [
			{
				"balloonText": "[[title]] of [[category]]:[[value]]",
				"fillAlphas": 1,
				"id": "AmGraph-1",
				"title": "Physical Sales",
				"type": "column",
				"valueField": "column-1"
			},
			{
				"balloonText": "[[title]] of [[category]]:[[value]]",
				"fillAlphas": 1,
				"id": "AmGraph-2",
				"title": "Online Sales",
				"type": "column",
				"valueField": "column-2"
			}
		],
		"guides": [],
		"valueAxes": [
			{
				"id": "ValueAxis-1",
				"title": "Copies Sold"
			}
		],
		"allLabels": [],
		"balloon": {},
		"legend": {
			"enabled": true,
			"useGraphSettings": true
		},
		"titles": [
			{
				"id": "Title-1",
				"size": 15,
				"text": "Previous Month Sales"
			}
		],
		"dataProvider": [
			{
				"column-1": "300",
				"column-2": "1000",
				"category": "January"
			},
			{
				"column-1": "350",
				"column-2": "1100",
				"category": "February"
			},
			{
				"column-1": "320",
				"column-2": "1200",
				"category": "March"
			},
			{
				"column-1": "400",
				"column-2": "100",
				"category": "April"
			},
			{
				"column-1": "250",
				"column-2": "900",
				"category": "May"
			},
			{
				"column-1": "300",
				"column-2": "1300",
				"category": "June"
			}
		]
	}
);

AmCharts.makeChart("chartdiv2",
	{
		"type": "serial",
		"categoryField": "category",
		"colors": [
			"#C0B283",
			"#FCD202",
			"#B0DE09",
			"#0D8ECF",
			"#2A0CD0",
			"#CD0D74",
			"#CC0000",
			"#00CC00",
			"#0000CC",
			"#DDDDDD",
			"#999999",
			"#333333",
			"#990000"
		],
		"startDuration": 1,
		"categoryAxis": {
			"gridPosition": "start"
		},
		"trendLines": [],
		"graphs": [
			{
				"balloonText": "[[title]] of [[category]]:[[value]]",
				"bullet": "round",
				"id": "AmGraph-1",
				"title": "Registrations",
				"valueField": "column-1"
			}
		],
		"guides": [],
		"valueAxes": [
			{
				"id": "ValueAxis-1",
				"title": "Users Registered"
			}
		],
		"allLabels": [],
		"balloon": {},
		"legend": {
			"enabled": true,
			"useGraphSettings": true
		},
		"titles": [
			{
				"id": "Title-1",
				"size": 15,
				"text": "Previous Months Registrations"
			}
		],
		"dataProvider": [
			{
				"category": "January",
				"column-1": "50"
			},
			{
				"category": "February",
				"column-1": "45"
			},
			{
				"category": "March",
				"column-1": "78"
			},
			{
				"category": "April",
				"column-1": "64"
			},
			{
				"category": "May",
				"column-1": "49"
			},
			{
				"category": "June",
				"column-1": "80"
			}
		]
	}
);

$(document).ready(function () {

    $.ajax({
        async: false,
        url: "tier3/userdetails",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            username = data.username;
            $('#adminWelcome').text(
				"Welcome back, " + username
			)
        }
    });
});