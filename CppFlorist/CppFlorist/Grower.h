#pragma once
#include <string>
#include <vector>

class FlowersBouquet;
class Gardener;

class Grower {
public:
	FlowersBouquet* prepareOrder(std::vector<std::string> flowers);
private:
	Gardener* gardener;
};
