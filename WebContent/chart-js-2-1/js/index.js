var path = window.location.pathname;
var page = path.split("/").pop();
var chart_id = localStorage.getItem("chart_id");
console.log(chart_id);

var data1={
  type: 'line',
  data: {
    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
    datasets: [
      {
        label: "Quantity of customers",
        data: [7.5,0,8.5,9.5,12,8,9,9,7,8,10,7,7.5,0,8.5,9.5,12,8,9,9,7,8,10,7,7.5,0,8.5,9.5,12,8,9,9,7,8,10,7,7.5,0,8.5,9.5,12,8,9,9,7,8,10,7,7.5,0,8.5,9.5,12,8,9,9,7,8,10,7]
      }
    ]
  }
};
var data2={
  type: 'line',
  data: {
    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
    datasets: [
      {
        label: "Number of connections",
        data: [40,34,54,44,65,62,46,57,46,47,56,48,40,34,54,44,65,62,46,57,46,47,56,48,40,34,54,44,65,62,46,57,46,47,56,48,40,34,54,44,65,62,46,57,46,47,56,48,40,34,54,44,65,62,46,57,46,47,56,48]
      }
    ]
  }
};
var data3={
  type: 'line',
  data: {
    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
    datasets: [
      {
        label: "Number of connected users",
        data: [50,41,61,54,52,58,46,17,46,47,56,40,50,41,61,54,52,58,46,17,46,47,56,40,50,41,61,54,52,58,46,17,46,47,56,40,50,41,61,54,52,58,46,17,46,47,56,40,50,41,61,54,52,58,46,17,46,47,56,40]
      }
    ]
  }
};


var data4={
  type: 'line',
  data: {
    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
    datasets: [
      {
        label: "AVG of active user's connections",
        data: [14,15,13,14,14,16,17,18,16,18,17,14,14,15,13,14,14,16,17,18,16,18,17,14,14,15,13,14,14,16,17,18,16,18,17,14,14,15,13,14,14,16,17,18,16,18,17,14,14,15,13,14,14,16,17,18,16,18,17,14]
      }
    ]
  }
};
var dataSet2 =[];
if (true){
var webSocket = new WebSocket("ws://localhost:8080/PswebMW/endpoint");
var echoText = '';
echoText.value = "";
var message = '';
//message='{"type":"OS","req":"root files","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3"}';
message='{"type":"OS","req":"root files","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3"}';

webSocket.onopen = function(message){ wsOpen(message);};
webSocket.onmessage = function(message){ wsGetMessage(message);};
webSocket.onclose = function(message){ wsClose(message);};
webSocket.onerror = function(message){ wsError(message);};
wsSendMessage();


}


//message='{"type":"OS","req":"RAM","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3"}';
//wsSendMessage();

//message='{"type":"OS","req":"RAM","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3"}';
//wsSendMessage();
function wsOpen(message){
	echoText.value += "Connected ... \n";
	
}
function wsSendMessage(){

	webSocket.send(message);
	//echoText.value += "Message sent to the server : " + message.value + "\n";
	echoText =  message;
	message = "";
}
function wsCloseConnection(){
	webSocket.close();
}
function wserror(message){
	echoText= "Error ... \n";
}
function wsGetMessage(message){
	//echoText.value += "Message received from to the server : " + message.data + "\n";
	echoText = message.data ;
		
		var messageAsJson =  JSON.parse(message.data);
		
		switch(messageAsJson.req) {
	    case "root files":
			parse_roots_jar("{'roots':[{'name':'root1','total_space':200,'used_space':70.5,'free_space':129.5,},{'name':'root2','total_space':500,'used_space':279.6,'free_space':220.4,}]}");
	        break;
	    case "RAM":
	    	parseRamJson('{"RAM": {"totalPhysical": 5.821, "freePhysical":0.125,"totalSwap": 0.695,"freeSwap": 0.694}}');
	        break;
	    case "CPU":
	    	parseCpuJson('{"CPU": {"overall": 20.5}}');
	        break;
		}

	
}

function parseCpuJson(CpuJson){
	debugger;
	var obj = JSON.parse(CpuJson);
	var totalPhysical=parseFloat (obj.CPU.overall);

}

function parseRamJson(RamJson){
	debugger;
	var result = JSON.stringify(eval("(" + RamJson + ")"));
	var obj = JSON.parse(RamJson);
	var totalPhysical=parseFloat (obj.RAM.totalPhysical);
	var freePhysical=parseFloat (obj.RAM.freePhysical);
	var usedPhysical =totalPhysical -freePhysical ;
	var totalSwap=parseFloat (obj.RAM.totalSwap);
	var freeSwap=parseFloat (obj.RAM.freeSwap);
	var usedSwap =totalSwap -freeSwap ;
}
function parse_roots_jar(myJson){
//	alert(2);
	//var result = JSON.parse(myJson);
	var result = JSON.stringify(eval("(" + myJson + ")"));

	//var text = "{ 'as':[{'name':'root1','total_space':200,'used_space':70.5,'free_space':129.5,},{'name':'root2','total_space':500,'used_space':279.6,'free_space':220.4,}]}";
	var text='{"roots": [{"name": "root1", "total_space":44.5,"used_space": 22.5,"free_space": 20},{"name": "root2", "total_space":34,"used_space":24,"free_space":10},{"name": "root3", "total_space":40,"used_space": 20,"free_space":20}]}'
	var obj = JSON.parse(text);
//	alert(obj.name)
//	alert(result[0]);
	for ( var aa in obj.roots){
//		alert(obj.roots[aa].name);
		var rootName=obj.roots[aa].name;
		var total=parseFloat (obj.roots[aa].total_space);
		var used =parseFloat (obj.roots[aa].used_space);
		var free= parseFloat (obj.roots[aa].free_space);
//		alert('total :'+ total+'    used :'+ used +'    free :'+ free);
		dataSet2[aa]=[rootName,total,used,free];
	}
//	alert(dataSet2[0]);
//	debugger;
	displayRoots(dataSet2);
	displayCpuChart();
	displayRamCharts();
	displayLineCharts();
}
function displayRamCharts(){
	displayRamPhysicalChart();
	displayRamSwapChart();
}



function displayRoots(dataset2){
	var dataSet = anychart.data.set(dataset2);
	
  var chart = anychart.column();
  
  chart.column(dataSet.mapAs({value:1,x:0})).name('Total');
  chart.column(dataSet.mapAs({value:2,x:0})).name('Used');
  chart.column(dataSet.mapAs({value:3,x:0})).name('Free');
  
  
  chart.grid(0,{layout:'vertical'})
  chart.barGroupsPadding(3)
  
  chart.legend(true);
  chart.title('Root Files System')
  chart.yScale().minimum(0);
  chart.container('root_system');
  chart.draw();



}


function displayCpuChart()
{
if ($('#container').length > 0 ) {


  var rawData = 67,
  data = getData(rawData);

function getData(rawData) {
  var data = [],
    start = Math.round(Math.floor(rawData / 10) * 10);
  data.push(rawData);
  for (i = start; i > 0; i -= 10) {
    data.push({
      y: i
    });
  }
  return data;
}

Highcharts.chart('container', {
  chart: {
    type: 'solidgauge',
    marginTop: 40
  },
  
  title: {
    text: 'CPU - load the overall system',
    style: {
      'font-size': '13px',
      'color':'#4682B4',
      'font-weight': 'bold',
    }
  },
  
  subtitle: {
    text: rawData+'%',
    style: {
      'font-size': '30px',
      'color':'#4682B4'
    },
    y: 170,
    zIndex: 7
  },

  tooltip: {
    enabled: false
  },

  pane: [{
    startAngle: -120,
    endAngle: 120,
    background: [{ // Track for Move
      outerRadius: '100%',
      innerRadius: '80%',
      backgroundColor: Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0.3).get(),
      borderWidth: 0,
      shape: 'arc'
    }],
    size: '90%',
    center: ['50%', '65%']
  }, {
    startAngle: -120,
    endAngle: 120,
    size: '90%',
    center: ['50%', '65%'],
    background: []
  }],

  yAxis: [{
    min: 0,
    max: 100,
    lineWidth: 2,
    lineColor: 'white',
    tickInterval: 10,
    labels: {
      enabled: false
    },
    minorTickWidth: 0,
    tickLength: 50,
    tickWidth: 5,
    tickColor: 'white',
    zIndex: 6,
    stops: [
      [0, '#fff'],
      [0.101, '#0f0'],
      [0.201, '#2d0'],
      [0.301, '#4b0'],
      [0.401, '#690'],
      [0.501, '#870'],
      [0.601, '#a50'],
      [0.701, '#c30'],
      [0.801, '#e10'],
      [0.901, '#f03'],
      [1, '#f06']
    ]
  }, {
    linkedTo: 0,
    pane: 1,
    lineWidth: 5,
    lineColor: 'white',
    tickPositions: [],
    zIndex: 6
  }],
  
  series: [{
    animation: false,
    dataLabels: {
      enabled: false
    },
    borderWidth: 0,
    color: Highcharts.getOptions().colors[0],
    radius: '100%',
    innerRadius: '80%',
    data: data
  }]
});

}
}
// if ($('#storagechart').length > 0 ) {
// var storage_Canvas = document.getElementById("storagechart");
// var storage_CTX = storage_Canvas.getContext('2d');

// // Global Options:
//  Chart.defaults.global.defaultFontColor = '#4682B4';
// //  Chart.defaults.global.defaultFontSize = 16;

// var storage_data = {
//     labels: ["Used", "Free"],
//       datasets: [
//         {
//             backgroundColor: [
//               '#4682B4',
//                 '#bfd2cd'],
//             data: [47,53],
// // Notice the borderColor 
//             borderWidth: [1,1]
//         }
//     ]
// };

// // Notice the rotation from the documentation.

// var storage_options = {
//         title: {
//                   display: true,
//                   text: 'Storage for root file'
//                              },
//         // rotation: -0.7 * Math.PI
// };


// // Chart declaration:
// var storage_Chart = new Chart(storage_CTX, {
//     type: 'doughnut',
//     data: storage_data,
//     options: storage_options
// });

// }

///////////////////////////////

//if ($('#CPUchart').length > 0 ) {
//
//var CPU_Canvas = document.getElementById("CPUchart");
//var CPU_CTX = CPU_Canvas.getContext('2d');
//
//// Global Options:
// Chart.defaults.global.defaultFontColor = '#4682B4';
////  Chart.defaults.global.defaultFontSize = 16;
//
//var CPU_data = {
//    labels: ["Utilization", ""],
//      datasets: [
//        {
//            backgroundColor: [
//              '#4682B4',
//                '#bfd2cd'],
//            data: [67,33],
//// Notice the borderColor 
//            borderWidth: [1,1]
//        }
//    ]
//};
//
//// Notice the rotation from the documentation.
//
//var CPU_options = {
//        title: {
//                  display: true,
//                  text: 'CPU - Physical Memory Size'
//                             },
//        // rotation: -0.7 * Math.PI
//};
//
//
//// Chart declaration:
//var CPU_Chart = new Chart(CPU_CTX, {
//    type: 'doughnut',
//    data: CPU_data,
//    options: CPU_options
//});
//
//
//}
///////////////////////////////


function displayRamPhysicalChart(){
if ($('#RAMchart').length > 0 ) {

var RAM_Canvas = document.getElementById("RAMchart");
var RAM_CTX = RAM_Canvas.getContext('2d');

// Global Options:
 Chart.defaults.global.defaultFontColor = '#4682B4';
//  Chart.defaults.global.defaultFontSize = 16;

var RAM_data = {
  labels: ["Free", "Used"],
  datasets: [
        {
            backgroundColor: [
              '#4682B4',
                '#bfd2cd'],
            data: [6, 2],
// Notice the borderColor 
            borderWidth: [1,1]
        }
    ]
};

// Notice the rotation from the documentation.

var options = {
        title: {
                  display: true,
                  text: 'RAM - Physical Memory Size'
                             },
        // rotation: -0.7 * Math.PI
};


// Chart declaration:
var RAM_Chart = new Chart(RAM_CTX, {
    type: 'pie',
    data: RAM_data,
    options: options
});
}
}

function displayRamSwapChart(){
if ($('#RAMswap').length > 0 ) {

  var RAM_swap_Canvas = document.getElementById("RAMswap");
  var RAM_swap_CTX = RAM_swap_Canvas.getContext('2d');
  
  // Global Options:
   Chart.defaults.global.defaultFontColor = '#4682B4';
  //  Chart.defaults.global.defaultFontSize = 16;
  
  var RAM_swap = {
      labels: ["Free", "Used"],
        datasets: [
          {
              backgroundColor: [
                '#4682B4',
                  '#bfd2cd'],
              data: [8, 5],
  // Notice the borderColor 
              borderWidth: [1,1]
          }
      ]
  };
  
  // Notice the rotation from the documentation.
  
  var options = {
          title: {
                    display: true,
                    text: 'RAM - Swap Size'
                               },
          // rotation: -0.7 * Math.PI
  };
  
  
  // Chart declaration:
  var RAM_swap_Chart = new Chart(RAM_swap_CTX, {
      type: 'pie',
      data: RAM_swap,
      options: options
  });
  
}
}


function displayDayData(){

newData=[];
currentData=lineChart1.data.datasets[0].data;
// for(var j=0;j<2;j++)
//   for(var i=0;i<currentData.length;i++)
//     newData.push(currentData[i]);

lineChart1.destroy();
lineChart1 = new Chart(ctx1, { type: 'line', data: {
    labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24"],
    datasets: [
      {
        label: "customers",
        data: newData
      }
    ]
  } }); 
}
function displayHourData(){
// alert(localStorage.getItem("oneDate"));

  newData=[];
  currentData=lineChart1.data.datasets[0].data;
  // for(var j=0;j<5;j++)
  //   for(var i=0;i<currentData.length;i++)
  //     newData.push(currentData[i]);
  
  lineChart1.destroy();
  lineChart1 = new Chart(ctx1, { type: 'line', data: {
      labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
      datasets: [
        {
          label: "customers",
          data: currentData
        }
      ]
    } }); 
  }
  function displayMinutesData(){

    newData=[];
    currentData=lineChart1.data.datasets[0].data;
    // for(var j=0;j<5;j++)
    //   for(var i=0;i<currentData.length;i++)
    //     newData.push(currentData[i]);
    
    lineChart1.destroy();
    lineChart1 = new Chart(ctx1, { type: 'line', data: {
        labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
        datasets: [
          {
            label: "customers",
            data: currentData
          }
        ]
      } }); 
    }
    function displaySecondsData(){

      newData=[];
      currentData=lineChart1.data.datasets[0].data;
      // for(var j=0;j<5;j++)
      //   for(var i=0;i<currentData.length;i++)
      //     newData.push(currentData[i]);
      
      lineChart1.destroy();
      lineChart1 = new Chart(ctx1, { type: 'line', data: {
          labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31", "32","33","34","35","36","37","38","39","40","41", "42","43","44","45","46","47","48","49","50","51", "52","53","54","55","56","57","58","59","60"],
          datasets: [
            {
              label: "customers",
              data: currentData
            }
          ]
        } }); 
      }


function displayLineCharts(){
if ($('#line-chart1').length > 0 ) {
if (chart_id !=0)
{
  var ctx1 = $("#line-chart1");
  var lineChart1 = new Chart(ctx1,displaySelectedChart(chart_id));
}
else {
  var ctx1 = $("#line-chart1");
  var lineChart1 = new Chart(ctx1,data1);
  }
} 

if ($('#line-chart2').length > 0) {

var ctx2 = $("#line-chart2");
var lineChart2 = new Chart(ctx2, data2);
}
if ($('#line-chart3').length > 0) {
var ctx3 = $("#line-chart3");
var lineChart3 = new Chart(ctx3,data3);
}

if ($('#line-chart4').length > 0) {

var ctx4 = $("#line-chart4");
var lineChart4 = new Chart(ctx4, data4);
}
}
function chooseChart(id){
  localStorage.setItem("chart_id", id);
  $(location).attr('href', 'page2.html');
}

function displaySelectedChart(id){
 
var data;
  switch(id) {
    case "1":
        data=data1;
        return data;
        break;
    case "2":
        data=data2;
        return data;
        break;
    case "3":
        data=data3;
        return data;
        break;
    case "4":
        data=data4;
        return data;
        break;

}

 
}
  // lineChart1 = new Chart($("#line-chart1"),data);
  // lineChart1 = new Chart(ctx1, { type: 'line', data: {
  //   labels: ["10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120"],
  //   datasets: [
  //     {
  //       label: "customers",
  //       data: [40,34,54,44,65,62,46,57,46,47,56,48]
  //     }
  //   ]
  // } }); 
