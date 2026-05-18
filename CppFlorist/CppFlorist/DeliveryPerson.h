#pragma once
#include <string>
#include <vector>

class Person;
class FlowersBouquet;

class DeliveryPerson {
public:
	void deliver(Person* recipient, FlowersBouquet* bouquet);
};