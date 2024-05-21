/**SERVICE MODULE 
 */
module otus.moryakovdv.task4.weatherservice {
	requires otus.moryakovdv.task4.coremod;
	requires otus.moryakovdv.task4.weatherprovider;
	requires otus.moryakovdv.task4.weatherdata;
	
	exports otus.moryakovdv.task4.service;
}