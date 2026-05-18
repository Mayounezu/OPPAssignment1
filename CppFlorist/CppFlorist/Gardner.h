#pragma once
#include <string>
#include <vector>

class FlowersBouquet;

class Gardener {
public:
	Gardener(std::string name);
	FlowersBouquet* prepareBouquet(std::vector<std::string> flowers);
private: 
	std::string name;
};